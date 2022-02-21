package jsoo.util.changeformat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class FileOpener {
		int nMode = 0;
		String bfCharset = ""; //�ԷµǴ� ���ڵ����
		String atCharset = ""; //��µǴ� ���ڵ����
	
	// ������ ��� ���� �˻� �� ó��
	public void findAllFilesInFolder(File folder) {
		FileInputStream inputStream = null;
		Charset inputCharset = null;
		InputStreamReader isr = null;
		Reader reader = null;
		int nRead = 0;
		StringBuffer stringBuffer = new StringBuffer();
		Writer writer = null;
		FileOutputStream outputStream = null;
		
		for (File file : folder.listFiles()) {
			if (!file.isDirectory()) {		
				System.out.println(file.getName());   //�����̸� ���
				
				try {
					inputStream = new FileInputStream(file);
					inputCharset = Charset.forName(bfCharset);
					isr = new InputStreamReader(inputStream, inputCharset);		
					reader = new BufferedReader(isr);
					
					while((nRead = reader.read()) > -1){
						stringBuffer.append((char)nRead);
					}
					reader.close();
					
					outputStream = new FileOutputStream(file);
					writer = new OutputStreamWriter(outputStream, atCharset);

					if(nMode == 1) writer.write("\uFEFF"); //utf8����ÿ��� 
					
					writer.write(stringBuffer.toString());
					stringBuffer.setLength(0);
					writer.close();
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				findAllFilesInFolder(file);
			}
		}
	}
	
	
	
    public void fileOpen(int mode) {
    	// 0: UTF-8 to MS949   1: MS949 to UTF-8
    	nMode = mode;
    	if(nMode == 0){
    		bfCharset = "utf-8";
    		atCharset = "MS949";
    	}
    	else if (nMode ==1){
    		bfCharset = "MS949";
    		atCharset = "utf-8";
    	}
        JFileChooser jfc = new JFileChooser();
        // ���� �Ǵ� ���丮 ���� �� �� �ֵ��� ����
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int returnVal = jfc.showOpenDialog(null);
        if(returnVal == 0) {
            System.out.println("���� ���⸦ �����߽��ϴ�.");
            File file = jfc.getSelectedFile();
            findAllFilesInFolder(file);
            
            JOptionPane.showMessageDialog(null,"���ϻ����Ϸ�");
        }
        else
        {
            System.out.println("���� ���⸦ ����Ͽ����ϴ�.");
        }
    }
	
	
	
}
