 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alttanjavaprolab1;



public class Nodes{//4 e göre ayarlıydı her şeyy
static int hedefSayisi=8;
int basimSayisi=0;
Nodes parent[]=new Nodes[hedefSayisi];///bu son güncelleme parentlerin hepsini tutabilmek için  ///11 mart yeni güncelleme engel sayısına göre güncelleme olmalı 
int  col;
int row;
int gCost[]=new int[hedefSayisi];
int hCost[]=new int[hedefSayisi];
int fCost[]=new int[hedefSayisi];
boolean start;
public static boolean goal[]=new boolean[hedefSayisi];// 2 tane idi dinamik olması için değiştiridiim kaç tane hedef tutulduğu alacağız
boolean solid;
boolean open;
boolean checked;
int nodeValue;
int yeter=0;
public Nodes(int row,int col){
    for (int i = 0; i < hedefSayisi; i++) {
        goal[i]=false;
    }
//    goal[0]=false;
//    goal[1]=false;
//    goal[2]=false;
//    goal[3]=false;/// buralar  nedir ?????
    this.row=row;
    this.col=col;
    //
   // this.setBackground(Color.WHITE);
  //  addActionListener(this);
    nodeValue=-2;//arkaplan rengi beyaz olması için 
    //adddactionlisterne(this) var            
}
public void setAsStart(){
    nodeValue=-3;//mavi başlangıç noktası için 
    start=true;
    //setBackground(Color.BLUE);
   // setForeground(Color.WHITE);
}
public void setAsGoal(int kacinciHedef){/// node value değerlerini genişletebilirim diğerlerine de verebilirim 
    
    nodeValue=-4;//sarı hedef noktası
    goal[kacinciHedef]=true;
    //setBackground(Color.RED);
    //setForeground(Color.white);
    
}
public void setAsSolid(){
    nodeValue=-5;//Engeller bu tarzda
    solid=true;
    //ilgili node nesnesinin solid değişkenini true yapıyor
    //setBackground(Color.gray);
    //setForeground(Color.gray);
   
 }

public void setAsOpen(){
    open=true;
}
public void setAsClose(){
    open=false;
}
public void setAscheckedFalse(){
    checked=false;
}

public void setAsChecked(int kacinciHedef,int row,int col){
  //  if( start==false && goal[0]==false && goal[1]==false && goal[2]==false&& goal[3]==false){
// if(ProLab.grid[row][col]==7||ProLab.grid[row][col]==8||ProLab.grid[row][col]==9||ProLab.grid[row][col]==10){
//        
//    }else{     
  ProLab.grid[row][col]=-8;//bakalım olcak mıııııııı  
// }     
   // }
    checked=true;
    yeter=kacinciHedef;
    
}
public void setAsPath(int row,int col){//// yol için ayrıyeten nasıl bir durum kullanılcakakkk acabaa ????
  // if( start==false && ProLab.goalReached[0]==false && ProLab.goalReached[1]==false && ProLab.goalReached[2]==false&& ProLab.goalReached[3]==false){
//    if(ProLab.grid[row][col]==7||ProLab.grid[row][col]==8||ProLab.grid[row][col]==9||ProLab.grid[row][col]==10){
//        
//    }else{
  ProLab.grid[row][col]=-9;//bakalım olcak mıııııııı  
    //  setBackground(Color.GREEN);
   // setForeground(Color.BLACK);
//      }
}
    
}


