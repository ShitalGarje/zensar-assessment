package Miniproject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextFileInput {
	
	//read Input text file
	public ArrayList<String> readtextinputfile() throws FileNotFoundException {  //
		 File file = new File("D:\\worksoft\\inputtextfile.txt");
		    Scanner sc = new Scanner(file);
		    ArrayList<String> data = new ArrayList<String>();
		    while (sc.hasNextLine()) {
				//System.out.println(sc.nextLine());
				data.add(sc.nextLine());
			}
		    System.out.println(data);
		    return data;
	}

}
