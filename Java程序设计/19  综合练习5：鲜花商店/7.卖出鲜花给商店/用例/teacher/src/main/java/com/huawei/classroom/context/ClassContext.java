package com.huawei.classroom.context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.google.gson.Gson;

/**
 * 用例判定上下文。
 */
public class ClassContext {

	private List<Testcase> testSuit = new ArrayList();
	private static File successFile;

	public ClassContext() {
		try {
			initOutFile();
		} catch (Exception e) {
			System.out.println("初始化文件失败!");
		}
	}

	private void initOutFile() throws IOException {
		String judgeSuccessOut = System.getProperty("judge.out.success.path");
		if (judgeSuccessOut != null && judgeSuccessOut != "") {
			successFile = new File(judgeSuccessOut);
			fillPath(successFile);
		}
	}

	/**
	 * 指定用例判定逻辑。
	 * 
	 * @param predicate
	 *            用例判定逻辑。
	 * @param <T>
	 * @return
	 */
	public <T> Testcase testcase(Predicate<T> predicate) {
		Testcase testcase = new Testcase();
		testcase.predicate(predicate);
		testSuit.add(testcase);
		return testcase;
	}

	/**
	 * 打印所有的测试结果。
	 */
	public void printResult() {
		List<TestResult> results = this.testSuit.stream().map(t -> t.getResult()).collect(Collectors.toList());
		Gson gson = new Gson();
		FileWriter fileWriter = null;
		try {
			System.out.println(gson.toJson(results));
			String result = gson.toJson(results) + "\n";
			if (successFile != null && successFile.exists()) {
				fileWriter = new FileWriter(successFile, true);
				fileWriter.write(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fileWriter);
		}

	}

	private static void fillPath(File file) throws IOException {
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			file.createNewFile();
		}
	}

	private static void close(FileWriter fileWriter) {
		if (fileWriter != null) {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
