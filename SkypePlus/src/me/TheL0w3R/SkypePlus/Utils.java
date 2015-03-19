package me.TheL0w3R.SkypePlus;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import me.TheL0w3R.SkypePlus.Listeners.SkypeMessageListener;
import ChatterBotApi.ChatterBotType;

import com.skype.Friend;
import com.skype.Skype;
import com.skype.SkypeException;

/**
 *
 * @author TheL0w3R
 */
public class Utils {

	static DefaultListModel fullListModel = new DefaultListModel();
	static DefaultListModel userListModel = new DefaultListModel();
	static ArrayList userNames = new ArrayList();
	static double AppVersion = 1.7;
	private final static String versionURL = "http://sksp.com.nu/version.html";
	private final static String changesURL = "http://sksp.com.nu/changelog.html";
	private static final String root = "update/";
	String changelog;
	double latest;
	static File oldCfg = new File("config.properties");
	static String dataFolder = System.getenv("APPDATA");
	static File groupFolder = new File(dataFolder + "/SkypePlus/Groups");
	static File groupFile = new File(dataFolder + "/SkypePlus/" + "groups.cfg");
	static ArrayList<String> userArray = new ArrayList<>();
	static DefaultListModel<String> refreshed = new DefaultListModel<String>();

	public static DefaultListModel refreshGroupList(int index) throws IOException {
		refreshed.clear();
		for (String name : getGroup(index, 0)) {
			refreshed.addElement(name);
		}
		return refreshed;
	}

	public static ChatterBotApi.ChatterBotType setType(int index) {
		if (index == 0) {
			return ChatterBotType.PANDORABOTS;
		} else if (index == 1) {
			return ChatterBotType.CLEVERBOT;
		} else if (index == 2) {
			return ChatterBotType.JABBERWACKY;
		} else {
			return null;
		}
	}

	public static String appendBotOutput(String message) {
		return message;
	}

	public static void removeStartUp() throws IOException {
		PrintWriter removestart = new PrintWriter(new FileWriter("removestart.bat"));

		removestart.println("reg delete HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v SkypePlus");
		removestart.println("exit");

		removestart.close();

		Runtime.getRuntime().exec("cmd /c start removestart.bat");
	}

	public static void addStartUp() throws IOException {
		PrintWriter startupper = new PrintWriter(new FileWriter("startupper.bat"));

		startupper.println("reg add HKLM\\Software\\Microsoft\\Windows\\CurrentVersion\\Run /v SkypePlus /d SkypePlus.exe");
		startupper.println("exit");

		startupper.close();

		Runtime.getRuntime().exec("cmd /c start startupper.bat");
	}

	public static void runChatBot() throws SkypeException {
		Skype.addChatMessageListener(new SkypeMessageListener());
		Skype.setDaemon(false);
	}

	public static void removeGroup(String groupName) {

		try {
			String file = readAllText(groupFile.toString());
			String[] arr = file.split("\n"); // every arr items is a line now.
			StringBuilder strb = new StringBuilder();
			PrintWriter pw = new PrintWriter(groupFile);
			for (String s : arr) {
				if (s.equals("<name>" + groupName + "</name><path>" + groupFolder + "\\" + groupName + ".txt</path>\n")) {
					continue;
				}
				strb.append(s); // If you want to split with new lines you can
								// use sb.append(s + "\n");
			}

			// strb.toString() //NUEVO ARCHIVO EDITADO
			pw.print(strb.toString());
			pw.close();

		} catch (Exception ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static String readAllText(String filename) throws Exception {
		StringBuilder sb = new StringBuilder();
		Files.lines(Paths.get(filename)).forEach(sb::append);
		return sb.toString();
	}

	public static String[] getGroup(int index, int isFullName) throws IOException {
		File groupTXT = new File(getIndexValues(index, 1)); // ES NULL??
		int length = countLines(groupTXT);
		String[][] data = new String[countLines(groupFile)][3];
		String ids = null;
		String names = null;

		String[] lines = new String[length];

		Scanner fileScanner = new Scanner(groupTXT);

		for (int i = 0; i < length; i++) {
			lines[i] = fileScanner.nextLine();
		}

		for (int i = 0; i < countLines(groupFile); i++) {
			data[index][0] = lines[0].substring(lines[0].indexOf("<ID>") + 4, lines[0].indexOf("</ID>"));
			data[index][1] = lines[1].substring(lines[1].indexOf("<Names>") + 7, lines[1].indexOf("</Names>"));
			data[index][2] = lines[2].substring(lines[2].indexOf("<Index>") + 7, lines[2].indexOf("</Index>"));
		}

		String[] users = data[index][0].substring(data[index][0].indexOf("[") + 1, data[index][0].indexOf("]")).split(", ");
		String[] fullNames = data[index][1].substring(data[index][1].indexOf("[") + 1, data[index][1].indexOf("]")).split(", ");
		String[] indexes = data[index][2].substring(data[index][2].indexOf("[") + 1, data[index][2].indexOf("]")).split(", ");

		if (isFullName == 0) {
			return fullNames;
		} else if (isFullName == 1) {
			return users;
		} else if (isFullName == 2) {
			return indexes;
		}
		return null;
	}

	public static void saveGroup(String groupName, ArrayList ids, ArrayList names, ArrayList indexes, boolean writeIndex) {
		try {
			PrintWriter indexWriter = new PrintWriter(new FileWriter(groupFile, true));
			PrintWriter groupWriter = new PrintWriter(groupFolder + "\\" + groupName + ".txt");

			if (writeIndex == true) {
				indexWriter.println("<name>" + groupName + "</name><path>" + groupFolder + "\\" + groupName + ".txt</path>");
			} else {
				// do nothing.
			}

			if (new File(groupFolder + "\\" + groupName + ".txt").exists()) {
				new File(groupFolder + "\\" + groupName + ".txt").delete();
				new File(groupFolder + "\\" + groupName + ".txt").createNewFile();
				groupWriter.println("<ID>" + ids + "</ID>");
				groupWriter.println("<Names>" + names + "</Names>");
				groupWriter.println("<Index>" + indexes + "</Index>");
			} else {
				groupWriter.println("<ID>" + ids + "</ID>");
				groupWriter.println("<Names>" + names + "</Names>");
				groupWriter.println("<Index>" + indexes + "</Index>");
			}

			indexWriter.close();
			groupWriter.close();

			ids.clear();
			names.clear();
			indexes.clear();

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	// public static ArrayList<String> transform(ArrayList users) {
	// try {
	// ArrayList transformed = new ArrayList();
	// for (int i = 0; i < users.size(); i++) {
	// Friend[] tempFriends = new Friend[users.size()];
	// if (users.get(i) != null) {
	// tempFriends[i] =
	// Skype.getContactList().getFriend(users.get(i).toString());
	// for (Friend friend : tempFriends) {
	// if (users.get(i).equals(friend)) {
	// transformed.add(friend.getFullName());
	// }
	// }
	// }
	// }
	// System.out.println(transformed);;
	// return transformed;
	// } catch (SkypeException ex) {
	// Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
	// }
	//
	// return null;
	// }
	public static void createSysFiles() {
		File f = new File(dataFolder + "/SkypePlus/Groups");
		if (!(f.exists())) {
			f.mkdirs();
		}
		if (!(groupFile.exists())) {
			try {
				groupFile.createNewFile();
			} catch (IOException ex) {
				Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public static int countLines(File file) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(file));
		try {
			byte[] c = new byte[1024];
			int count = 0;
			int readChars = 0;
			boolean empty = true;
			while ((readChars = is.read(c)) != -1) {
				empty = false;
				for (int i = 0; i < readChars; ++i) {
					if (c[i] == '\n') {
						++count;
					}
				}
			}
			return (count == 0 && !empty) ? 1 : count;
		} finally {
			is.close();
		}
	}

	// DEVUELVE LA LINEA SELECCIONADA DEL ARCHIVO INDEX! EJEMPLO: LINEA1=
	// Utils.readLine(0);
	public static String readIndexLine(int line) throws IOException {
		String[] lines = new String[countLines(groupFile)/* +1 */];
		try {
			Scanner fileScanner = new Scanner(groupFile/* .toString() */);

			for (int i = 0; i < countLines(groupFile); i++) {
				lines[i] = fileScanner.nextLine();
			}

		} catch (FileNotFoundException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return lines[line];
	}

	public static String getIndexValues(int index, int typeOfReturn) throws IOException {
		String[][] data = new String[countLines(groupFile) + 1][2];
		String name = null;
		String path = null;

		for (int i = 0; i < countLines(groupFile); i++) {
			data[i][0] = readIndexLine(i).substring(readIndexLine(i).indexOf("<name>") + 6, readIndexLine(i).indexOf("</name>"));
			data[i][1] = readIndexLine(i).substring(readIndexLine(i).indexOf("<path>") + 6, readIndexLine(i).indexOf("</path>"));
		}

		try {
			name = data[index][0];
			path = data[index][1];
		} catch (Exception e) {
			System.out.println("Error getting data.");
			e.printStackTrace();
		}

		if (typeOfReturn == 0) {
			return name;
		} else if (typeOfReturn == 1) {
			return path;
		} else {
			System.out.println("The value must be 0 or 1");
		}
		return null;
	}

	public static String getAppData() {
		return dataFolder;
	}

	public static File getOldCfg() {
		return oldCfg;
	}

	public static void removeOldCfg() {
		oldCfg.delete();
	}

	public static DefaultListModel getFriends(int nameOrUser) {
		fullListModel.clear();
		userListModel.clear();
		try {
			Friend[] allFriends = Skype.getContactList().getAllFriends();

			System.out.println("Skype contact list lenght: " + allFriends.length);

			for (Friend friend : allFriends) {
				String fullName = friend.getFullName();
				String userName = friend.toString();
				if (fullName.isEmpty()) {
					fullName = friend.getId();
				}

				fullListModel.addElement(fullName);
				userListModel.addElement(userName);
			}
		} catch (SkypeException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
			JOptionPane.showMessageDialog(null, "Error while connecting to Skype.\nMake sure to open Skype before this.\nIf Skype is already opened, click 'Yes' when a popup appears in Skype.", "SkypePlus Error", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		if (nameOrUser == 0) {
			return fullListModel;
		} else if (nameOrUser == 1) {
			return userListModel;
		} else {
			System.out.println("You must specify a valid data type.");
		}

		return null;
	}

	public static String getUserNames(int index, boolean isFull) {
		String[] friendStrings = null;
		try {
			friendStrings = new String[Skype.getContactList().getAllFriends().length];
			Friend[] allFriends = Skype.getContactList().getAllFriends();
			for (int i = 0; i < allFriends.length; i++) {
				friendStrings[i] = allFriends[i].toString();
			}
		} catch (SkypeException ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (isFull == false) {
			return friendStrings[index];
		} else if (isFull == true) {
			return Arrays.toString(friendStrings);
		}
		return null;
	}

	public static double getVersion() {
		return AppVersion;
	}

	public static void sendMessage(String user, String msg, String replacer) {
		try {
			String newMsg = null;
			if (msg.contains("<NAME>")) {
				newMsg = msg.replaceAll("<NAME>", replacer);
			} else {
				newMsg = msg;
			}
			Skype.chat(user).send(newMsg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getData(String address) throws Exception {
		URL url = new URL(address);
		InputStream html = null;
		html = url.openStream();
		int c = 0;
		StringBuffer buffer = new StringBuffer("");

		while (c != -1) {
			c = html.read();
			buffer.append((char) c);
		}

		return buffer.toString();
	}

	public static String getLatestVersion() {
		String data = versionURL;
		try {
			data = getData(versionURL);
		} catch (Exception ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));
	}

	public static String getChangeLog() {
		String data = changesURL;
		try {
			data = getData(changesURL);
		} catch (Exception ex) {
			Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
		}
		return data.substring(data.indexOf("[changelog]") + 11, data.indexOf("[/changelog]"));
	}

	public static void downloadFile(String link, JEditorPane infoPane, JProgressBar progress) throws MalformedURLException, IOException {
		URL url = new URL(link);
		URLConnection conn = url.openConnection();
		InputStream is = conn.getInputStream();
		int max = conn.getContentLength();
		infoPane.setText(infoPane.getText() + "\nDownloding file...\nDownload Size(compressed): " + max + " Bytes");
		progress.setMaximum(max);
		BufferedOutputStream fOut = new BufferedOutputStream(new FileOutputStream(new File("update.zip")));
		byte[] buffer = new byte[32 * 1024];
		int bytesRead = 0;
		int in = 0;
		while ((bytesRead = is.read(buffer)) != -1) {
			in += bytesRead;
			fOut.write(buffer, 0, bytesRead);
			progress.setValue(in);
		}
		fOut.flush();
		fOut.close();
		is.close();
		infoPane.setText(infoPane.getText() + "\nDownload Complete!");
	}

	public static void copyFiles(File f, String dir) throws IOException {
		File[] files = f.listFiles();
		for (File ff : files) {
			if (ff.isDirectory()) {
				new File(dir + "/" + ff.getName()).mkdir();
				copyFiles(ff, dir + "/" + ff.getName());
			} else {
				copy(ff.getAbsolutePath(), dir + "/" + ff.getName());
			}

		}
	}

	public static void copy(String srFile, String dtFile) throws FileNotFoundException, IOException {

		File f1 = new File(srFile);
		File f2 = new File(dtFile);

		InputStream in = new FileInputStream(f1);

		OutputStream out = new FileOutputStream(f2);

		byte[] buf = new byte[1024];
		int len;
		while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
		in.close();
		out.close();
	}

	public static void unzip(JEditorPane infoPane) throws IOException {
		int BUFFER = 2048;
		BufferedOutputStream dest = null;
		BufferedInputStream is = null;
		ZipEntry entry;
		ZipFile zipfile = new ZipFile("update.zip");
		Enumeration e = zipfile.entries();
		(new File(root)).mkdir();
		while (e.hasMoreElements()) {
			entry = (ZipEntry) e.nextElement();
			infoPane.setText(infoPane.getText() + "\nExtracting: " + entry);
			if (entry.isDirectory()) {
				(new File(root + entry.getName())).mkdir();
			} else {
				(new File(root + entry.getName())).createNewFile();
				is = new BufferedInputStream(zipfile.getInputStream(entry));
				int count;
				byte data[] = new byte[BUFFER];
				FileOutputStream fos = new FileOutputStream(root + entry.getName());
				dest = new BufferedOutputStream(fos, BUFFER);
				while ((count = is.read(data, 0, BUFFER)) != -1) {
					dest.write(data, 0, count);
				}
				dest.flush();
				dest.close();
				is.close();
			}
		}
	}

	public static void launch() {
		try {
			Runtime.getRuntime().exec("java -jar Updater.jar SkypePlus_v1.4.exe update.zip SkypePlus_v1.5.exe");
			System.exit(0);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void cleanup(JEditorPane infoPane) {
		infoPane.setText(infoPane.getText() + "\nPreforming clean up...");
		try {
			File f = new File("update.zip");
			f.delete();
			remove(new File(root));
			new File(root).delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void remove(File f) {
		File[] files = f.listFiles();
		for (File ff : files) {
			if (ff.isDirectory()) {
				remove(ff);
				ff.delete();
			} else {
				ff.delete();
			}
		}
	}
}
