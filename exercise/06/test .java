import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class test {
	

	public static void main(String[] args) {
		
		ArrayList mylist = new ArrayList();
		int i ;
		try {
			FileReader fr = new FileReader("/Users/HawkTorng/Desktop/test.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line=br.readLine()) != null) {
				mylist.add(line);
//			    System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writetotxt(mylist);
		
		

	}
	private static void writetotxt(ArrayList mylist) {
		
		int counter = 16;
		ArrayList finallist = new ArrayList();
		ArrayList jumplist = new ArrayList();
		File a = new File("/Users/HawkTorng/Desktop/test2.txt");
		try {
			a.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList jumpcount2 = new ArrayList();
			for(int jumpcount = 0;mylist.size()>jumpcount;jumpcount++) {	
					if(mylist.get(jumpcount).toString().indexOf("(")!= -1) {
						String changetext = mylist.get(jumpcount).toString().replace("(", "");
						String changetext2 = changetext.replace(")", "").trim();
						jumplist.add(changetext2);
						if(jumpcount2.isEmpty()) {
							jumpcount2.add(jumpcount);
						}
						else {
							jumpcount2.add(jumpcount-1);
						}
					}
			}
			int i;
			ArrayList text = new ArrayList();
			String symtable [][] = 
				{{"R0","0"},
				 {"R1","1"},
				 {"R","2"},
				 {"R3" ,"3"},
				 {"R4","4"},
				 {"R5","5"},
				{"R6" ,"6"},
				{"R7","7"},
				{"R8","8"},
				{"R9","9"},
				{"R10","10"},
				{"R11","11"},
				{"R12","12"},
				{"R13" ,"13"},
				{"R14","14"},
				{"R15","15"},
				{"SP" ,"0"},
				{ "LCL","1"},
				{"ARG","2"},
				{"THIS","3"}, 
				{ "THAT","4"},
				{"KBD" ,"24576"},
				{"SCREEN","16384"},
				};
			
			for(i = 0;i<mylist.size();i++) {
				String a1 = (mylist.get(i)).toString();
				String b = a1.trim();
				ArrayList symlist = new ArrayList();
				for(int sym = 0;sym<symtable.length-1;sym++) {
					symlist.add(symtable[sym][0]);
				}
				if(String.valueOf(b.charAt(0)).equals("@")) { //a
					String insA1 = "0";
					String insnumber = b.substring(1,b.length());
					if(isNumeric(insnumber)) {
				    int j = Integer.parseInt(insnumber);
				    String formatstr = "%15s";
				    String binary1 = String.format(formatstr,Integer.toBinaryString(j));
				    String binary0 = binary1.replace(" ","0");
				    String insA = insA1 + binary0;
				    finallist.add(insA);
					}
					else if(symlist.contains(insnumber)){
						int symnum = symlist.indexOf(insnumber);
						String formatstr = "%15s";
					    String binary1 = String.format(formatstr,Integer.toBinaryString(symnum));
					    String binary0 = binary1.replace(" ","0");
					    String insA = insA1 + binary0;
					    finallist.add(insA);
					}
					else if(!jumplist.contains(b.substring(1))){
						if(text.contains(b)) {
							int idx = text.indexOf(b);
							int sametext = idx+16;
							String formatstr = "%15s";
							String binary2 = String.format(formatstr,Integer.toBinaryString(sametext));
							String binary0 = binary2.replace(" ","0");
							String insA2 = insA1 + binary0;
							finallist.add(insA2);
						}
						else {
						String formatstr = "%15s";
						String binary2 = String.format(formatstr,Integer.toBinaryString(counter));
						String binary0 = binary2.replace(" ","0");
						String insA2 = insA1 + binary0;
						finallist.add(insA2);
						counter++;
						}
						text.add(b);
					}
					
					else {
						String jump = b.substring(1,b.length());
						System.out.println(jump);
						if(jumplist.contains(jump)) {
							int tobin = Integer.parseInt(jumpcount2.get(jumplist.indexOf(jump)).toString());
							String formatstr = "%16s";
						    String binary1 = String.format(formatstr,Integer.toBinaryString(tobin));
						    String binary0 = binary1.replace(" ","0");
							finallist.add(binary0);
						}
						
					}
					
				    
				}
				else if (!String.valueOf(b.charAt(0)).equals("(")){   // c
					
					String insCc[] = {"101010","111111","111010","001100","110000","001101","110001"," 001111"," 110011","011111","110111","001110","110010","000010","010011","000111","000000","010101"};
					String insC1 = "111";
					if(b.indexOf("=")==-1) {
						String insCj;
						String insCd = "000";
						String insC2[] = b.split(";");
						String insCa = "0";
						String insCc1;
						if(insC2[0].equals("D")) {
							insCc1 = "001100";
							if(insC2[1].equals("JGT")) {
								insCj = "001";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JEQ")) {
								insCj = "010";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JGE")) {
								insCj = "011";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JLT")) {
								insCj = "100";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JNE")) {
								insCj = "101";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JLE")) {
								insCj = "110";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
							else if(insC2[1].equals("JMP")) {
								insCj = "111";
								String finalinsc = insC1+insCa+insCc1+insCd+insCj;
								finallist.add(finalinsc);
							}
						}
						else {
							insCc1 = "101010";
						
						if(insC2[1].equals("JGT")) {
							insCj = "001";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JEQ")) {
							insCj = "010";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JGE")) {
							insCj = "011";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JLT")) {
							insCj = "100";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JNE")) {
							insCj = "101";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JLE")) {
							insCj = "110";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						else if(insC2[1].equals("JMP")) {
							insCj = "111";
							String finalinsc = insC1+insCa+insCc1+insCd+insCj;
							finallist.add(finalinsc);
						}
						}
					}
					else {
						
						String insC3[] = b.split("=");
						String insCa2;
						String insCj2 = "000";
						String insCdarray[] = {"0","M","D","MD","A","AM","AD","AMD"};
						if(insC3[1].indexOf("M") ==-1) {
							insCa2 = "0";
							if(insC3[1].equals("0")) {								
								String insCc2 = insCc[0];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
								
							}
							else if(insC3[1].equals("1")) {
								String insCc2 = insCc[1];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("-1")) {
								String insCc2 = insCc[2];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D")) {
								String insCc2 = insCc[3];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("A")) {
								String insCc2 = insCc[4];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("!D")) {
								String insCc2 = insCc[5];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("!A")) {
								String insCc2 = insCc[6];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D+1")||insC3[1].equals("1+D")) {
								String insCc2 = insCc[7];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("A+1")||insC3[1].equals("1+A")) {
								String insCc2 = insCc[8];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D-1")) {
								String insCc2 = insCc[9];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("A-1")) {
								String insCc2 = insCc[10];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D+A")||insC3[1].equals("A+D")) {
								String insCc2 = insCc[11];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D-A")) {
								String insCc2 = insCc[12];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("A-D")) {
								String insCc2 = insCc[13];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D&A")||insC3[1].equals("A&D")) {
								String insCc2 = insCc[14];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D|A")) {
								String insCc2 = insCc[15];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							
							
							
						}
						else {
							insCa2 = "1";
						    if(insC3[1].equals("M")) {
								String insCc2 = insCc[4];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("!M")) {
								String insCc2 = insCc[6];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("M+1")||insC3[1].equals("1+A")) {
								String insCc2 = insCc[8];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("M-1")) {
								String insCc2 = insCc[10];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D-M")) {
								String insCc2 = insCc[12];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("M-D")) {
								String insCc2 = insCc[13];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D&M")||insC3[1].equals("M&D")) {
								String insCc2 = insCc[14];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
							else if(insC3[1].equals("D|M")) {
								String insCc2 = insCc[15];
								for(int k = 0;k<insCdarray.length;k++) {
									if(insC3[0].equals(insCdarray[k])) {
										String formater = "%3s";
										String insCd2 = String.format(formater,Integer.toBinaryString(k));
										String insCd3 = insCd2.replace(" ", "0");	
										String finalinsC3 = insC1+insCa2+insCc2+insCd3+insCj2;
										finallist.add(finalinsC3);
									}
								}
							}
						}
					}
					
					
				}else {
//					String thisline = "------------";
//					finallist.add(thisline);
				}
			FileWriter fw = new FileWriter("/Users/HawkTorng/Desktop/test2.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			String line = null; 
			Object list[] = finallist.toArray();
		    String[] stringArray = Arrays.copyOf(list, list.length+1, String[].class);
			int counter2;
	        for(counter2 = 0;(line = stringArray[counter2]) != null&&counter2<stringArray.length-1;counter2++){
	            bw.write(line);  
	            bw.newLine();  
	            bw.flush(); 
	        }
	        }
		    }
	
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		}

	public static boolean isNumeric(String str){
		
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
			return true;
	}
	
	
	
	
	
	
}
