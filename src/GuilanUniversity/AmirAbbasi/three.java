package GuilanUniversity.AmirAbbasi;
import java.util.*;
/**
 * Project three
 * Amir Abbasi
 * 
 */
import javax.swing.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
public class three {
	static JFrame jk=new JFrame();
	 static char [][] board=new char [10][10];
	 static final String ENGLISH="qwertyuiopasdfghjklzxcvbnm";//keyboard baraye dashtane horoof
	public static void main(String[] args) {
	
 JButton start=new JButton("Start a Game");//anajame bazi
 JButton exit=new JButton("EXit");//khorooj
 JButton about=new JButton("About");
 JButton generating=new JButton("Generate");//sakhte jadval

 JFrame mainPage=new JFrame();//frame baraye main page
 mainPage.setLayout(null);

 ////////tanzime location va size dokme ha va ezafe be frame////////
start.setBounds(300, 200, 300, 150);
about.setBounds(300 , 600, 300,150);
exit.setBounds(300, 800, 300, 150);
generating.setBounds(300, 400, 300, 150);
mainPage.setSize(1000,1030);
mainPage.add(about);
mainPage.add(exit);
mainPage.add(generating);
mainPage.add(start);
mainPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
mainPage.setVisible(true);
/////////////////////////////////////////////////

///////////////////GENERATING////////////////////
generating.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		generate();//sakhte jadval
		
	}
});
//////////////////////////////////////////////


////////////START/////////////////////////////
start.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainPage.setVisible(false);
		play();//bazi
		
	}
});
///////////////////////////////////////////

/////////////////EXIT////////////////////
exit.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
	System.exit(0);//exit
	}
});
///////////////////////////////////////


/////////////////ABOUT/////////////////////
about.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		JOptionPane.showMessageDialog(null, "Producted by Amir Abbasi\nFTS\nEnjoy ;)", "About", 0, null);
		
		
	}
});
///////////////////////////////////////
	
	}
	/**
	 * void
	 * az karbar kalame migirad va jadvali ra be soorate tasadofi misazad
	 */
	public static void generate() {
		String tmpN=JOptionPane.showInputDialog("Please number of words");//tedade kalamt be String
		int n=Integer.parseInt(tmpN);//tedad be Integer
		int i,j;
		String [] words=new String [n];//arraye baraye kalamat
		String [] adress=new String[n];//arraye baraye Data haye kalamta(shamele motode jagozari dar jadval va index)
		
		for(i=0;i<n;i++)
			words[i]=JOptionPane.showInputDialog("word "+String.valueOf(i+1)).toLowerCase();//gereftane kalamat(karbar agar horoof bozorg ham vared konad koochak dar nazar gerefte mishavad)
		for(i=0;i<10;i++)
			for(j=0;j<10;j++)
				board[i][j]=' ';
		for(i=0;i<n;i++)
		adress[i]=makeRandom(words[i]);//gozashtane kalamat dar jadval be soorate random ba makeRandom() va sepas gozashtane Data dar address[]
		
		/////////////por kardane jadval be sooorate random ba horuf
		Random rnd=new Random();
		for(i=0;i<10;i++) {
			for(j=0;j<10;j++)
			{if(board[i][j]==' ')
			{
				board[i][j]=ENGLISH.charAt(Math.abs(rnd.nextInt())%ENGLISH.length());
			}}}
		/////////////////////////////////////////////////////////
		
		String strBoard="";//jadval dar yek bod baraye namayesh ba JOPtionpane
	for(i=0;i<10;i++) {
		for(j=0;j<10;j++)
			strBoard+=String.valueOf(board[i][j]+" ");
		strBoard+="\n";
	}
	JOptionPane.showMessageDialog(null, strBoard);//namayeshe jadvale sakhte shode

	File a=new File("files/Data");
	String files[];//tmp baraye mohasebe tedade file ha
	files=a.list();//tedade file ha
	try {
	FileWriter write=new FileWriter("files/"+String.valueOf(files.length+1)+".txt");//name file ha adad tabiei ast va yek vahed yek vahed ziad mikonad
	/////////jadval dar file neveshte mishavad/////////
	for(i=0;i<10;i++) {
		for(j=0;j<10;j++) 
			write.write(board[i][j]+" \r");	
		write.write("\n");
	}

	write.flush();
	write.close();
	////////////////////////////////
	
	
	//////////Data neveshte mishavad//////
	write=new FileWriter("files/Data/"+String.valueOf(files.length+1)+".answers.txt");
	for(i=0;i<words.length;i++) {
		write.write(words[i]+":"+adress[i]);//word:metod:index(i):index(j)
		
	}
	write.flush();
	write.close();
	}
	////////////////////////////////////
	catch(IOException e) {
		JOptionPane.showMessageDialog(null, "A problem occured when writting in file", "Eror", 0, null);
	}
		
	}
	/**
	 * vazife gozashtane kalamat dar jadval be soorate tasadofi bar ohde darad.
	 * yek khane az jadval ra be soorate tasadofi dar nazar migirad va check mikonad dar khane haye badi mishavad edame kalame ra jagozari kard ya na
	 * @param word
	 * @return Data ra bar migradanad(DATA=word:metod:i:j)
	 */
public static String makeRandom(String word) {
int i,j,z;
	Random rnd=new Random();
	int aw=Math.abs(rnd.nextInt())%6+1;//sakhtane adadi tasadofi beyne 1 ta 6
	boolean flag=true;//flage yaftane adad
	int x=0,y=0,s;//s:shomrande baraye tedade khane haye draye sharayete monaseb(khali ya mosvai harfe kalame)
	if(aw==1) {//////amoodi(bala be payin)////
		while(flag==true) {
			s=0;
		x=Math.abs(rnd.nextInt())%10;
		y=Math.abs(rnd.nextInt())%10;
	
		for(i=x;i<10;i++)
		{if(s==word.length())
			break;
		if(board[i][y]==' ' || board[i][y]==word.charAt(s))//agar 
			{if(s==0)
				{
					x=i;//index sabt mishavad(khane aval ast)
					s++;
				}
				else
					s++;
			}
			else//shomarande reset mishavad
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			j=0;
		for(i=x;i<x+word.length();j++,i++)
			board[i][y]=word.charAt(j);
		}}}
	if(aw==2) {///////ofoghi(chap be rast)//////
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=y;i<10;i++)
		{if(s==word.length())
			break;
		if(board[x][i]==' ' || board[x][i]==word.charAt(s))
			{
				if(s==0)//yani khane aval
				{
					y=i;//index sabt mishavad
					s++;
				}
				else
					s++;
			}
			else//shomarande reset mishavad
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			j=0;
		for(i=y;i<y+word.length();j++,i++)
			board[x][i]=word.charAt(j);
		}}}
	if(aw==3) {/////bala be payin chap be rast////
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=x,j=y;i<10 && j<10;j++,i++)
		{if(s==word.length())
			break;
		if(board[i][j]==' ' || board[i][j]==word.charAt(s))
			{
				if(s==0)//yani khaane aval ast
				{
					x=i;
					y=j;
					s++;
				}
				else
					s++;
			}
			else//shamrande reset mishavad
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			z=0;
			for(i=x,j=y;i<x+word.length() && j<y+word.length();j++,i++,z++)
			board[i][j]=word.charAt(z);
		}}
	}
	if(aw==4) {/////payin be bala va rast be chap///
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=x,j=y;i>=0 && j>=0;j--,i--)
		{if(s==word.length())
			break;
		if(board[i][j]==' ' || board[i][j]==word.charAt(s))
			{
				if(s==0)//
				{
					x=i;
					y=j;
					s++;
				}
				else
					s++;
			}
			else
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			z=0;
			for(i=x,j=y;i>x-word.length() && j>y-word.length();j--,i--,z++)
			board[i][j]=word.charAt(z);
		}}
	}
	
	if(aw==5) {/////bala be payin va rast be chap///
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=x,j=y;i<10 && j>=0;j--,i++)
		{if(s==word.length())
			break;
		if(board[i][j]==' ' || board[i][j]==word.charAt(s))
			{
				if(s==0)
				{
					x=i;
					y=j;
					s++;
				}
				else
					s++;
			}
			else
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			z=0;
			for(i=x,j=y;i<x+word.length() && j>y-word.length();j--,i++,z++)
			board[i][j]=word.charAt(z);
		}}
	}
	if(aw==6) {///////payin be bala va chap be rast////
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=x,j=y;i>=0 && j<10;j++,i--)
		{if(s==word.length())
			break;
		if(board[i][j]==' ' || board[i][j]==word.charAt(s))
			{
				if(s==0)
				{
					x=i;
					y=j;
					s++;
				}
				else
					s++;
			}
			else
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			z=0;
			for(i=x,j=y;i>x-word.length() && j<y+word.length();j++,i--,z++)
			board[i][j]=word.charAt(z);
		}}
	}
	if(aw==7) {//////amoodi(payin be bala)////
		while(flag==true) {
			s=0;
		x=Math.abs(rnd.nextInt())%10;
		y=Math.abs(rnd.nextInt())%10;
	
		for(i=x;i>=0;i--)
		{if(s==word.length())
			break;
		if(board[i][y]==' ' || board[i][y]==word.charAt(s))//agar 
			{if(s==0)
				{
					x=i;//index sabt mishavad(khane aval ast)
					s++;
				}
				else
					s++;
			}
			else//shomarande reset mishavad
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			j=0;
		for(i=x;i>x-word.length();j++,i--)
			board[i][y]=word.charAt(j);
		}}}
	if(aw==8) {///////ofogh(rast be chap)//////
		while(flag==true) {
			s=0;
			 x=Math.abs(rnd.nextInt())%10;
			 y=Math.abs(rnd.nextInt())%10;
		for(i=y;i>=0;i--)
		{if(s==word.length())
			break;
		if(board[x][i]==' ' || board[x][i]==word.charAt(s))
			{
				if(s==0)
				{
					y=i;
					s++;
				}
				else
					s++;
			}
			else
				s=0;
		}
		if(s>=word.length()) {
			flag=false;
			j=0;
		for(i=y;i>y-word.length();j++,i--)
			board[x][i]=word.charAt(j);
		}}}
	return String.valueOf(aw)+":"+String.valueOf(x)+":"+String.valueOf(y)+"#";//metod:i:j# bargardande mishavad
}
/**
 * 
 * @return 0
 * dalil int dar nazar gerfetan in ast ke dar soorate borooze khata dar khandae file,tabe return konad va tabe motevaghef shavad
 * karbar 9 nobat darad
 */
public static int play() {

	int i,j,temp;//temp:baraye khandane file

	
	File a=new File("files/Data");
	String files[];//temp baraye mohasebe tedade file ha
	files=a.list();//tedade file ha
	Random rnd=new Random();
	int aw=0;
	try {
		aw=Math.abs(rnd.nextInt())%files.length+1;//name file besoorate random
	FileReader reader=new FileReader("files/"+String.valueOf(aw)+".txt");//yek file be soorate random baz mishavad
	temp=1;
	i=0;
	j=0;
while(temp!=-1)
{temp=reader.read();
	if(temp>=97 && temp<=122) {//yani agar caractere khoochak ast
		if(j==10) {
			j=0;
			i++;
		}
		board[i][j]=(char)temp;
	
	j++;}
}
reader.close();
}
	catch(IOException | ArithmeticException e) {
		JOptionPane.showMessageDialog(null, "A problem occured!Maybe there ins't any file in \"/files\"", "Error", 0, null);
		main(null);
		return 0;//tabe motevaghef mishavad
	}
	
	String tmpAns="";//tmp ast va dar do ja estefade shode:1-khandane file va rikhtane an daroone tmpAns2-voroodi gereftan az karbar
	String []answers=null;
	try {
		FileReader reader=new FileReader("files/Data/"+String.valueOf(aw)+".answers.txt");
	temp=1;
	while(temp!=-1) {
		temp=reader.read();
		tmpAns+=String.valueOf((char)temp);
	}
	tmpAns=tmpAns.substring(0,tmpAns.length()-1);//tmpAns=answer1:Data#answer2:Data...
	answers=tmpAns.split("#");//javab ha hamrah Data split mishavand
	}
	catch(IOException e) {
		JOptionPane.showMessageDialog(null, "A problem occured!Maybe there ins't any file in \"/files/temp\"", "Error", 0, null);
		main(null);
		return 0;
	}
	String solved=" ",wrongs=" ";//solved:javab haye dorost;wrongs:javab haye ghalat
	int turn,numberOfanswers=0;//trun:tedade nobat;numberOfanswers:tedade javab haye dorost
	
	Panel panel=new Panel("","","",9);//panele bazi sakhte mishavad

	jk.add(panel);
	jk.setSize(1000,1030);

	jk.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	jk.setVisible(true);

	for(turn=1;turn<10;turn++){//karbar 9 nobat darad
tmpAns=JOptionPane.showInputDialog(null,"Please enter your word");//kalame az karabar gerefte mishavad
		if(solved.indexOf(" "+tmpAns+" ")!=-1 || wrongs.indexOf(" "+tmpAns+" ")!=-1) {//baraye check karadane voroodie tekrari
			panel.updater(solved,"You have entered this word before!",wrongs,9-turn);
			panel.repaint();
			continue;
		}
		for(j=0;j<answers.length;j++) {//vodroodie karabar dar javabha gashte mishavad
			if(answers[j].substring(0,answers[j].indexOf(":")).equals(tmpAns)){
				numberOfanswers++;
				solved+=tmpAns+" ";
				String tmp[]=answers[j].split(":");
				switch(tmp[1]) {//be horoofe bozorg tabdl mishavad
				case "1"://amoodi(bala be payin)
					for(int f=Integer.parseInt(tmp[2]);f<Integer.parseInt(tmp[2])+tmpAns.length();f++) {
						board[f][Integer.parseInt(tmp[3])]=Character.toUpperCase(board[f][Integer.parseInt(tmp[3])]);
					}
					break;
				case "2"://ofoghi(chap be rast)
					for(int f=Integer.parseInt(tmp[3]);f<Integer.parseInt(tmp[3])+tmpAns.length();f++) {
						board[Integer.parseInt(tmp[2])][f]=Character.toUpperCase(board[Integer.parseInt(tmp[2])][f]);
					}
					break;
				case "3"://bala be payin va chap be rast
					for(int f=Integer.parseInt(tmp[2]),k=Integer.parseInt(tmp[3]);f<Integer.parseInt(tmp[2])+tmpAns.length();f++,k++) {
						board[f][k]=Character.toUpperCase(board[f][k]);
					}
					break;
				case "4"://payin be bala va rast be chap
					for(int f=Integer.parseInt(tmp[2]),k=Integer.parseInt(tmp[3]);f>Integer.parseInt(tmp[2])-tmpAns.length();f--,k--) {
						board[f][k]=Character.toUpperCase(board[f][k]);
					}
					break;
				case "5"://bala be payin va rast be chap
					for(int f=Integer.parseInt(tmp[2]),k=Integer.parseInt(tmp[3]);k>Integer.parseInt(tmp[3])-tmpAns.length();f++,k--) {
						board[f][k]=Character.toUpperCase(board[f][k]);
					}
					break;
				case "6"://payin be bala va chap be rast
					for(int f=Integer.parseInt(tmp[2]),k=Integer.parseInt(tmp[3]);f>Integer.parseInt(tmp[2])-tmpAns.length();f--,k++) {
						board[f][k]=Character.toUpperCase(board[f][k]);
					}
					break;
				case "7"://amoodi(payn be bala)
					for(int f=Integer.parseInt(tmp[2]);f>Integer.parseInt(tmp[2])-tmpAns.length();f--) {
						board[f][Integer.parseInt(tmp[3])]=Character.toUpperCase(board[f][Integer.parseInt(tmp[3])]);
					}
					break;
				case "8"://ofoghi(rast be chap)
					for(int f=Integer.parseInt(tmp[3]);f>Integer.parseInt(tmp[3])-tmpAns.length();f--) {
						board[Integer.parseInt(tmp[2])][f]=Character.toUpperCase(board[Integer.parseInt(tmp[2])][f]);
						}
					break;
				}
				break;
			}
			
		}
	
		if(j==answers.length) {//yani peyda nashode va break nashode
			wrongs+=tmpAns+" ";
	panel.updater(solved,"Doesn't exist!",wrongs,9-turn);}
		else//yani peyda shode va break shode
			panel.updater(solved,"Found!",wrongs,9-turn);
		panel.repaint();
		if(numberOfanswers==answers.length)//barande shodan
		{
			
			panel.updater(solved,"Win!You are very clever!",wrongs,10-turn);
			panel.repaint();
			break;
		}
	}
	if(turn==10) {//yani kalamat peyda nashode va break nashode
		panel.updater(solved,"GAME OVER!",wrongs,10-turn);
		panel.repaint();
		
		
	}
		int bit=JOptionPane.showOptionDialog(null, "What do you want to do?", "bLaCkRose", 0, 0, null,new String[] {"Play agian","Main menu","Exit"}, null);
		switch(bit) {
		case 0:
			play();
			break;
		case 1:
			main(null);
			break;
		case 2:
			System.exit(0);
			
		}
		return 0;
	}


}
