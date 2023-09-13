public class Main {

     private static boolean allProcessesFinished(Process[] processes) {
        for (Process p : processes) {
            if (p.getProcessState() != States.EXIT) {
                return false;
            }
        }
        return true;
    }

    private static boolean noneRunning(Process[] processes){
        for(Process p : processes){
            if(p.getProcessState() == States.RUNNING){
                System.out.println("Error: Some process is already running");
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Process p0 = new Process(0, 10000);
        Process p1 = new Process(1, 5000);
        Process p2 = new Process(2, 7000);
        Process p3 = new Process(3, 3000);
        Process p4 = new Process(4, 3000);
        Process p5 = new Process(5, 8000);
        Process p6 = new Process(6, 2000);
        Process p7 = new Process(7, 5000);
        Process p8 = new Process(8, 4000);
        Process p9 = new Process(9, 10000);

        Process[] processes = {p0, p1, p2, p3, p4, p5, p6, p7, p8, p9};

        Cpu cpu = new Cpu();

        while(!allProcessesFinished(processes) && noneRunning(processes)) {
            for(Process p : processes) {
                if(p.getProcessState()!=States.EXIT) {
                    cpu.run(p);
                } else {
                    System.out.println("Process " + p.getPid() + " turn, but it is already finished!");
                    continue;
                }
            }
        }
        System.out.println("\n=============All processes finished=============");
        for(Process p : processes) {
            p.printProcess();
        }
    }
}
