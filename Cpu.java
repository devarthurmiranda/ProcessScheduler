import java.util.Random;

public class Cpu {
    private static final int QUANTUM = 1000;
    Random rand = new Random();
    private static int chance = 100;

    public void run(Process process) {

        if(process.getCyclesNeeded() <= 0) {
            process.setProcessState(States.EXIT);
            System.out.println("Process " + process.getPid() + " finished");
            return;
        }

        for(int i = 0; i < QUANTUM; i++) {
            checkState(process);
        }
        process.setCyclesNeeded(process.getCyclesNeeded() - QUANTUM);
        process.setNumCpu(process.getNumCpu() + 1);
        System.out.println("Quantum ended for process " + process.getPid());
        process.setProcessState(States.READY);
    }

    private void checkState(Process process) {
        if(process.getProcessState() == States.READY || process.getProcessState() == States.RUNNING) {
            process.setProcessState(States.RUNNING);
            if(rand.nextInt(chance) < 5) {
                process.setProcessState(States.BLOCKED);
                System.out.println("Process " + process.getPid() + " is blocked");
                process.setNumEntExi(process.getNumEntExi() + 1);
            }
            process.setProgramCounter(process.getProgramCounter() + 1);
            process.setProcessTime(process.getProcessTime() + 1);
            
        } else if(process.getProcessState() == States.BLOCKED) {
            if(rand.nextInt(chance) < 30) {
                process.setProcessState(States.READY);
                System.out.println("Process " + process.getPid() + " went from blocked to ready");
            }
        }
        
    }
}
