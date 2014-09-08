package examples

object stateful {

    type Action = () => Unit
    abstract class Simulation {
        def currentTime: Int
        def afterDelay(delay: Int, action: => Action)
        def run()
    }
    class Wire {
        private var sigVal = false
        private var actions: List[Action] = List()
        def getSignal = sigVal
        def setSignal(s: Boolean) =
            if (s != sigVal) {
                sigVal = s
                actions.foreach(action => action())
            }
        def addAction(a: Action) {
            actions = a :: actions; a()
        }
    }
    def inverter(input: Wire, output: Wire) {
        def invertAction() {
            val inputSig = input.getSignal
            afterDelay(InverterDelay) { output setSignal !inputSig }
        }
        input addAction invertAction
    }

    def andGate(a1: Wire, a2: Wire, output: Wire) {
        def andAction() {
            val a1Sig = a1.getSignal
            val a2Sig = a2.getSignal
            afterDelay(AndGateDelay) { output setSignal (a1Sig & a2Sig) }
        }
        a1 addAction andAction a2 addAction andAction
    }
}