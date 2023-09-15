import java.util.Random;

public class Cpu {
    private static final int QUANTUM = 1000;
    Random rand = new Random();
    private static int chance = 100;

    private void checkState(Process process) {
        // First quantum cycle case
        if(process.getProcessState() == States.READY) {
            process.setProcessState(States.RUNNING);
            System.out.println("Process " + process.getPid() + " is now running");
            process.setProgramCounter(process.getProgramCounter() + 1);
            process.setProcessTime(process.getProcessTime() + 1);

            // Block chance
            if(rand.nextInt(chance) < 5) {
                process.setProcessState(States.BLOCKED);
                System.out.println("Process " + process.getPid() + " got blocked");
                process.setNumEntExi(process.getNumEntExi() + 1);
            }  
        }

        // Currently running case
        if(process.getProcessState() == States.RUNNING) {
            process.setProgramCounter(process.getProgramCounter() + 1);
            process.setProcessTime(process.getProcessTime() + 1);

            // Block chance
            if(rand.nextInt(chance) < 5) {
                process.setProcessState(States.BLOCKED);
                System.out.println("Process " + process.getPid() + " got blocked");
                process.setNumEntExi(process.getNumEntExi() + 1);
            }
        }
    }

    public void run(Process process) {
        // Setting process to exit case
        if(process.getCyclesNeeded() <= 0) {
            process.setProcessState(States.EXIT);
            System.out.println("Process " + process.getPid() + " finished");
            return;
        }

        // Process blocked before quantum
        if(process.getProcessState() == States.BLOCKED) {
            // Unblock chance
            if(rand.nextInt(chance) < 30) {
                process.setProcessState(States.READY);
                System.out.println("Process " + process.getPid() + " went from blocked to ready");
                process.setProgramCounter(process.getProgramCounter() + 1);
                process.setProcessTime(process.getProcessTime() + 1);
            } else {
                System.out.println("Process " + process.getPid() + " is still blocked");
                process.setNumCpu(process.getNumCpu() + 1);
                System.out.println("\n======================Quantum ended for process " + process.getPid()+"======================\n");
                return;
            }
        }

        // Executing quantum
        for(int i = 0; i < QUANTUM; i++) {
            // Process blocked during quantum
            if(process.getProcessState() == States.BLOCKED) {
                process.setCyclesNeeded(process.getCyclesNeeded() - i);
                break;
            }
            checkState(process);
        }

        // Process ended without being blocked
        if(process.getProcessState()!=States.BLOCKED){
            // Making sure process will not be running after quantum
            if(process.getProcessState()==States.RUNNING){
                process.setProcessState(States.READY);
                System.out.println("Process " + process.getPid() + " went from running to ready");
            }
            process.setCyclesNeeded(process.getCyclesNeeded() - QUANTUM);
            process.setNumCpu(process.getNumCpu() + 1);
        }
        System.out.println("\n======================Quantum ended for process " + process.getPid()+"======================\n");
    }
}
