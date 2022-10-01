package ru.khomyakov;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class PomodoroTimer {
    private final static int barSize = 30;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Enter time for work and for rest");

        Scanner scanner = new Scanner(System.in);
        var cmd = scanner.nextLine().split(" ");

        int workTime = 1;
        int restTime = 1;
        boolean isHelp = false;

        for (int i = 0; i < cmd.length; i++) {
            switch (cmd[i]) {
                case "-w" -> workTime = Integer.parseInt(cmd[++i]);
                case "-r" -> restTime = Integer.parseInt(cmd[++i]);
                case "--help" -> {
                    System.out.println("Pomodoro is an application to improve personal efficiency\n" +
                            "-w     - work time\n" +
                            "-r     - rest time\n" +
                            "--help - help\n");
                    isHelp = true;
                }
            }

        }

        if (isHelp) return;

        long startTime = System.currentTimeMillis();
        timer(workTime, restTime);
        long endTime = System.currentTimeMillis();
        System.out.println("Timer worked " + (endTime - startTime)/(1000 * 60) + " min");
    }

    private static void timer(int workTime, int restTime) throws InterruptedException {
        printProgress("Work progress: ", workTime);
        printProgress("Rest progress: ", restTime);
    }

    private static void printProgress(String process, int timeInMin) throws InterruptedException {
        int timeOfBarElem = (int) (60.0 * timeInMin / barSize);
        timeOfBarElem = timeOfBarElem == 0? 1: timeOfBarElem;

        for (int i = 0; i <= barSize; i++){
            double x = i;

            double percent = (x * timeOfBarElem / (timeInMin * 60)) * 1000;

            x = Math.round(x * timeOfBarElem / (timeInMin * 60) * 10) / 10.0;

            percent = Math.round(percent) / 10.0;

            System.out.print(process + percent + "% " + (" ").repeat(5 - (String.valueOf(percent).length())) + "[" + ("#").repeat(i) + ("-").repeat(barSize - i)+"]    ( " + x +" min / " + timeInMin +" min )"+  "\r");

            TimeUnit.SECONDS.sleep(timeOfBarElem);

        }
        System.out.println();
    }
}
