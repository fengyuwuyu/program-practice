package com.ll.program.practice.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Random;

public class PathDemo {

	public static void main(String[] args) throws IOException, InterruptedException {
		PathDemo demo = new PathDemo();
//		demo.testPath();
//		demo.testCombine();
//		demo.testWalkFileTree();
//		demo.testResourse();
//		demo.testCreateAndDelete(false);
//		demo.testCopyAndMove();
		demo.readAndWrite();
		// while (true) {
		// Thread.sleep(1000);
		// }
	}
	
	private void readAndWrite() throws IOException {
		Path path = Paths.get("E:/java_test/nio", "a.txt");
		
		testCreateAndDelete(true);
		
		//write
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.WRITE)) {
			for (int i = 0; i < 4; i++) {
				writer.write("hello world! i = " + i + "\n\r");
			}
		}
		
		//read1
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line = reader.readLine();
			while (line != null) {
				System.out.println(line);
				line = reader.readLine();
			}
		}
		
		//read2
		List<String> lines = Files.readAllLines(path);
		System.out.println(lines);
	}
	
	private void testCopyAndMove() throws IOException {
		testCreateAndDelete(true);
		
		//copy
		Path path = Paths.get("E:/java_test/nio", "a.txt");
		int randomInt = new Random().nextInt();
		Path copyPath = Paths.get("E:/java_test/nio", "b" + randomInt + ".txt");
		Files.copy(path, copyPath);
		
		//move
		Path movePath = Paths.get("E:/java_test/nio/mv", "a.txt");
		Files.move(path, movePath, StandardCopyOption.REPLACE_EXISTING);
		
	}
	
	private void testCreateAndDelete(boolean createIfAbsent) throws IOException {
		Path path = Paths.get("E:/java_test/nio", "a.txt");
		if(!Files.exists(path)) {
			//创建文件的同时并制定读写等权限
//			Set<PosixFilePermission> perms = PosixFilePermissions.fromString("rw-rw-rw-");
//			FileAttribute<?> attrs = PosixFilePermissions.asFileAttribute(perms );
			Files.createFile(path);
		} else {
			System.out.println("file exists, delete it");
			if(!createIfAbsent) {
				Files.deleteIfExists(path);
			}
			
		}
		
	}
	
	private void testResourse() throws IOException {
		Path dir = Paths.get("D:/mongo_test");
		DirectoryStream<Path> stream = Files.newDirectoryStream(dir);
		for (Path path : stream) {
			System.out.println(path);
			if(!Files.isDirectory(path)) {
				Files.copy(path, System.out);
			}
		}
		
	}

	private void testPath() throws IOException {
		Path path = Paths.get("D:/");
		WatchService watcher = FileSystems.getDefault().newWatchService();
		path.register(watcher, StandardWatchEventKinds.ENTRY_CREATE);
	}

	private void testCombine() {
		Path path1 = Paths.get("D:/");
		Path path2 = Paths.get("F:\\ll\\开发文档\\大数据\\视频\\day33\\02.游戏项目---架构分析_.avi");

		System.out.println(path1.relativize(path2));
	}

	private void testWalkFileTree() throws IOException {
		Path path = Paths.get("D:/mongo");
		FileVisitor<? super Path> visitor = new FileVistor();;
		Files.walkFileTree(path, visitor);
	}

	class FileVistor extends SimpleFileVisitor<Path> {

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if(file.toString().endsWith("wt")) {
				System.out.println(file.getFileName());
			}
			return FileVisitResult.CONTINUE;
		}

	}
}
