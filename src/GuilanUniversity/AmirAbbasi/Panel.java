package GuilanUniversity.AmirAbbasi;
import javax.swing.*;
import java.awt.*;
public class Panel extends JPanel  {
	String solves;//javab haye dorost
	String status;//vaziat
	String wrongs;//javab haye ghalat
	int life;//tedade joon
public void paintComponent(Graphics gh) {
	super.paintComponent(gh);
	int w=this.getWidth();
	int h=this.getHeight();

	/////tarsime do mostatile zard ba kadr haye meshki/////
	gh.setColor(Color.yellow);
	gh.fillRect(15, 15, w-30, 45);//baraye "Answers"
	gh.fillRect(15, 65, w-30, 45);//baraye "status"
	gh.setColor(Color.black);
	gh.drawRect(15, 15, w-30, 45);
	gh.drawRect(15, 65, w-30, 45);
	//////////////////////////////////////
	
	
gh.drawRect(15,170, w-30, 800);//kadre kalamat
gh.setFont(new Font("TimesRoman",Font.PLAIN,30));//taghyire andaze font
gh.drawString("Answers:"+solves, 25, 46);//neveshtane javab haye dorost
gh.setFont(new Font("TimesRoman",Font.PLAIN,20));//taghire font
gh.drawString("Wrongs:"+wrongs,25, 95);//neveshtane tedade ghalat
if(status.equals("Doesn't exist!") || status.equals("You have entered this word before!" ) || status.equals("GAME OVER!")) {//dar soorate ghalat,tekrariya bakhtan mostatile ghermez rasm mishavad
	gh.setColor(Color.red);
	gh.fillRect(15, 115,500, 45);}
else if(status.equals("Found!") || status.equals("Win!You are very clever!")) {//dar soorate javabe dorost ya barande shodan mostatili sabz rasm mishavad
	gh.setColor(Color.GREEN);
	gh.fillRect(15, 115,460, 45);}



gh.setColor(Color.black);
gh.drawString(status, 20, 140);//neveshtane vaziat
gh.setFont(new Font("TimesRoman",Font.PLAIN,50));
char board[][];
board=three.board;


int i,j;
//////////////joon///////////
gh.setColor(Color.black);
gh.fillRect(490, 115,(w-30)/2,45);
gh.setFont(new Font("TimesRoman",Font.PLAIN,30));
gh.setColor(Color.white);
gh.drawString("Life:", 510, 145);
gh.setColor(Color.red);
gh.setFont(new Font("TimesRoman",Font.PLAIN,50));
for(i=life;i>=1;i--)//be tedade joon dayere ghermez rasm mishavad
	gh.fillOval(930-(i-1)*40, 120, 30, 30);
//////////////////////////////


//////////////neveshtane jadval////////////
gh.setColor(Color.black);
for(i=0;i<10;i++) {
	for(j=0;j<10;j++) {
		gh.drawString(String.valueOf(board[i][j]),45+j*96,230+80*i);
	}
}
//////////////////////////////////
}
/**
 * tabe sazande
 * @param solves javab haye dorost
 * @param status vaziat
 * @param wrongs ghalatha
 * @param life joon
 */
public Panel(String solves,String status,String wrongs,int life) {
	this.solves=solves;
	this.status=status;
	this.wrongs=wrongs;
	this.life=life;
}
public void updater(String solves,String status,String wrongs,int life) {
	this.solves=solves;
	this.status=status;
	this.wrongs=wrongs;
	this.life=life;
}
}

