package org.biogen.ecosystem.view;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.WatchKey;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.io.IOException;
import static java.nio.file.StandardWatchEventKinds.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WatchEventExample {

    private final static String directory1 = "/Users/deveshkandpal/Code/Aed/"
            + "aed_fall_2016_project_devesh_kandpal_001619561/Biogen_Integrated_System/"
            + "src/org/biogen/ecosystem/view/drug";

    private final static String directory2 = "/Users/deveshkandpal/Code/Aed/"
            + "aed_fall_2016_project_devesh_kandpal_001619561/Biogen_Integrated_System/"
            + "src/org/biogen/ecosystem/view/medicare";

    private final static String directory3 = "/Users/deveshkandpal/Code/Aed/"
            + "aed_fall_2016_project_devesh_kandpal_001619561/Biogen_Integrated_System/"
            + "src/org/biogen/ecosystem/view/chemical";

    public static void main(String[] args)
            throws Exception {
        new WatchEventExample().initiate();
    }

    private void initiate() {

        BlockingQueue<String> fileDirectoryQueue = new ArrayBlockingQueue<String>(1000);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        String[] fileArray = {directory1, directory2, directory3};
        for (String fileDir : fileArray) {
            Worker worker = new Worker(fileDirectoryQueue, fileDir);
            executor.execute(worker);
        }

        ExecutorService consumerExecutor = Executors.newFixedThreadPool(3);
        Consumer consumer = new Consumer(fileDirectoryQueue);
        consumerExecutor.execute(consumer);

    }

    @SuppressWarnings("unchecked")
    private void doWatch(String watchDirectory,
            BlockingQueue<String> fileDirectoryQueue)
            throws IOException, InterruptedException {
        System.out.println("invoking do watch 1");
        WatchService watchService = FileSystems.getDefault().newWatchService();

        Path path = Paths.get(watchDirectory);
        WatchKey watchKey = path.register(watchService, ENTRY_CREATE);

        System.out.println("Watch service registered dir: " + path.toString());

        for (;;) {

            WatchKey key;

            try {
                // System.out.println("Waiting for key to be signalled...");
                key = watchService.take();
            } catch (InterruptedException ex) {
                System.out.println("Interrupted Exception");
                return;
            }

            List<WatchEvent<?>> eventList = key.pollEvents();
            //System.out.println("Process the pending events for the key: "
            //    + eventList.size());

            for (WatchEvent<?> genericEvent : eventList) {

                WatchEvent.Kind<?> eventKind = genericEvent.kind();
                //  System.out.println("Event kind: " + eventKind);

                if (eventKind == OVERFLOW) {

                    continue; // pending events for loop
                }

                WatchEvent<Path> pathEvent = (WatchEvent<Path>) genericEvent;
                Path file = pathEvent.context();

                fileDirectoryQueue.put(file.toString());
            }

            eventList.stream().forEach(event -> {
                WatchEvent.Kind<?> eventKind = event.kind();
                if (eventKind != OVERFLOW) {
                    WatchEvent<Path> pathEvent = (WatchEvent<Path>) event;
                    Path file = pathEvent.context();
                    try {
                        fileDirectoryQueue.put(file.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            });

            boolean validKey = key.reset();
            //System.out.println("Key reset");
            //System.out.println("");

            if (!validKey) {
                //  System.out.println("Invalid key");
                break; // infinite for loop
            }

        } // end infinite for loop

        watchService.close();
        //System.out.println("Watch service closed.");
    }

    class Worker extends Thread {

        private BlockingQueue<String> fileLocationQueue;
        private String watchDirectory;

        Worker(BlockingQueue<String> fileLocationQueue, String watchDirectory) {

            this.fileLocationQueue = fileLocationQueue;
            this.watchDirectory = watchDirectory;
        }

        public void run() {
            try {
                new WatchEventExample().doWatch(this.watchDirectory, this.fileLocationQueue);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    class Consumer extends Thread {

        private BlockingQueue<String> fileLocationQueue;

        Consumer(BlockingQueue<String> fileLocationQueue) {

            this.fileLocationQueue = fileLocationQueue;

        }

        public void run() {
            try {

                while (true) {

                    if (!fileLocationQueue.isEmpty()) {

                        String fileLocation = fileLocationQueue.take();
                        System.out.println("Location pulled : " + fileLocation);

                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
