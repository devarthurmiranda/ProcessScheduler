import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Process {
    private int pid;
    private int processTime;
    private int programCounter;
    private States processState;
    private int numEntExi;
    private int numCpu;
    private int cyclesNeeded;

    public Process(int pid, int cyclesNeeded) {
        this.pid = pid;
        this.cyclesNeeded = cyclesNeeded;
        this.processTime = 0;
        this.programCounter = processTime + 1;
        this.processState = States.READY;
        this.numEntExi = 0;
        this.numCpu = 0;

    }

    public void printProcess() {
        System.out.println("Process " + this.pid + " has been in the CPU " + this.numCpu + " times");
        System.out.println("Process " + this.pid + " has " + this.numEntExi + " of I/O operations");
        System.out.println("Process " + this.pid + " has " + this.processTime + " of processing cycles");
        System.out.println("Process " + this.pid + " has " + this.programCounter + " of program time");
        System.out.println("Process " + this.pid + " state is " + this.processState);
        System.out.println();
    }


    public void saveProcessLog(){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("processLog.txt", true));
            writer.write("Process " + this.pid + " has been in the CPU " + this.numCpu + " times\n");
            writer.write("Process " + this.pid + " has " + this.numEntExi + " of I/O operations\n");
            writer.write("Process " + this.pid + " has " + this.processTime + " of processing cycles\n");
            writer.write("Process " + this.pid + " has " + this.programCounter + " of program time\n");
            writer.write("Process " + this.pid + " state is " + this.processState + "\n");
            writer.write("\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setProcessState(States processState) {
        this.saveProcessLog();
        this.processState = processState;
    }

    public int getPid() {
        return pid;
    }

    public int getProcessTime() {
        return processTime;
    }

    public int getCyclesNeeded() {
        return cyclesNeeded;
    }

    public void setProcessTime(int processTime) {
        this.processTime = processTime;
    }

    public int getProgramCounter() {
        return programCounter;
    }

    public void setProgramCounter(int programCounter) {
        this.programCounter = programCounter;
    }

    public States getProcessState() {
        return processState;
    }

    public int getNumEntExi() {
        return numEntExi;
    }

    public void setNumEntExi(int numEntExi) {
        this.numEntExi = numEntExi;
    }

    public int getNumCpu() {
        return numCpu;
    }

    public void setNumCpu(int numCpu) {
        this.numCpu = numCpu;
    }

    public void setCyclesNeeded(int cyclesNeeded) {
        this.cyclesNeeded = cyclesNeeded;
    }

}
