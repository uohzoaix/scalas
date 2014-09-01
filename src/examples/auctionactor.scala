package examples

import scala.actors.TIMEOUT

object auctionactor {

    import scala.actors.Actor
    import java.util.Date
    abstract class AuctionMessage
    case class Offer(bid: Int, client: Actor) extends AuctionMessage
    case class Inquire(client: Actor) extends AuctionMessage
    abstract class AuctionReply
    case class Status(asked: Int, expire: Date) extends AuctionReply
    case object BestOffer extends AuctionReply
    case class BeatenOffer(maxBid: Int) extends AuctionReply
    case class AuctionConcluded(seller: Actor, client: Actor) extends AuctionReply
    case object AuctionFailed extends AuctionReply
    case object AuctionOver extends AuctionReply

    class Auction(seller: Actor, minBid: Int, closing: Date) extends Actor {
        val timeToShutdown = 36000000 // msec
        val bidIncrement = 10
        def act() {
            var maxBid = minBid - bidIncrement
            var maxBidder: Actor = null
            var running = true
            while (running) {
                receiveWithin((closing.getTime() - new Date().getTime())) {
                    case Offer(bid, client) =>
                        if (bid >= maxBid + bidIncrement) {
                            //如果出价高于上次出价（其他出价者所出的价格）与价差之和
                            if (maxBid >= minBid)
                                //如果上次出价者的出价高于最小出价，那么向上次出价者发送价格被吃消息，价格为当前的出价
                                maxBidder ! BeatenOffer(bid)
                            //将当前竞价赋给maxBid
                            maxBid = bid;
                            //当前出价者赋给maxBidder,并且向当前出价者发送最高出价消息
                            maxBidder = client; client ! BestOffer
                        } else {
                            //如果当前出价者的出价小于上次出价与价差之和则说明该次出价无效，向当前出价者发送价格被吃消息，价格为上次的出价
                            client ! BeatenOffer(maxBid)
                        }
                    case Inquire(client) =>
                        //向当前出价者发送竞价关闭消息
                        client ! Status(maxBid, closing)
                    case TIMEOUT =>
                        //如果在指定时间内未完成竞价
                        if (maxBid >= minBid) {
                            //向最后竞价者和提出竞价者发送竞价结束消息
                            //最后竞价者收到谁提出的竞价
                            //提出竞价者收到谁出了最高价（赢得了竞价）
                            val reply = AuctionConcluded(seller, maxBidder)
                            maxBidder ! reply; seller ! reply
                        } else {
                            //向竞价提出者发送竞价失败消息
                            seller ! AuctionFailed
                        }
                        receiveWithin(timeToShutdown) {
                            case Offer(_, client) => client ! AuctionOver
                            case TIMEOUT => running = false
                        }
                }
            }
        }
    }
}