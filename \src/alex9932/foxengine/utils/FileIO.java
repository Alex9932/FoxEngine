package alex9932.foxengine.utils;

import java.io.File;
import java.io.IOException;

public class FileIO {

	// Overriding java.io.File methods
	public boolean setWritable(String file, boolean writable) {
		return new File(file).setWritable(writable, true);
	}

	public boolean setReadable(String file, boolean readable) {
		return new File(file).setReadable(readable, true);
	}

	public boolean delete(String file) {
		return new File(file).delete();
	}

	public boolean mkdir(String file) {
		return new File(file).mkdir();
	}
	
	public boolean createNewFile(String file) throws IOException{
		return new File(file).createNewFile();
	}
	
	public boolean canRead(String file) {
		return new File(file).canRead();
	}
	
	public boolean canWrite(String file) {
		return new File(file).canWrite();
	}

	public boolean isDirectory(String file) {
		return new File(file).isDirectory();
	}

	public boolean exists(String file) {
		return new File(file).exists();
	}

	// Reading and writing methods
	public String readFile(String path) {
		try (java.io.FileReader reader = new java.io.FileReader(path)) {
			int c;
			String str = "";
			while ((c = reader.read()) != -1) {
				str = str + (char) c;
			}
			return str;
		} catch (IOException ex) {
			return null;
		}
	}

	public void writeFile(String path, String text) {
		try (java.io.FileWriter writer = new java.io.FileWriter(path)) {
			writer.write(text);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public void appendFile(String path, String text) {
		String f = readFile(path);
		try (java.io.FileWriter writer = new java.io.FileWriter(path)) {
			writer.write(f);
			writer.write(text);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}