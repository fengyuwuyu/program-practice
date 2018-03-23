package com.ll.program.practice.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileWatchServiceTest {

	
	public static void main(String[] args) throws IOException, InterruptedException {
		WatchService watchService = FileSystems.getDefault().newWatchService();
		Path dir = FileSystems.getDefault().getPath("E:/java_test/nio");
		WatchKey key = dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
		
		while(true) {
			key = watchService.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				System.out.format("file changed, event = %s %n", event.context());
				if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
				}
			}
			key.reset();
		}
		
	}
}
