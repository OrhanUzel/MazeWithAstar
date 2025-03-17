

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package alttanjavaprolab1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.event.KeyListener;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import javax.accessibility.AccessibleContext;
import javax.imageio.ImageIO;
import javax.swing.JList;
public class ProLab extends JFrame implements KeyListener {

    public    static ArrayList<String>listeBulunanlar=new ArrayList<>();
    public static    ArrayList<String> listeBulunanHazineler = new ArrayList();
// JList jListeBulunanlar= new JList((ListModel) listeBulunanlar);
    
    
    
    int rowEmerald;
    int colEmerald;
    int rowCopper;
    int colCopper;
    int rowSilver;
    int colSilver;
    int rowGolden;
    int colGolden;
    int rowEmerald1;
    int colEmerald1;
    int rowCopper1;
    int colCopper1;
    int rowSilver1;
    int colSilver1;
    int rowGolden1;
    int colGolden1;
    
    
    
    
     ArrayList<Nodes> bulunanHedefler = new ArrayList();
    
    static int kacinciHedefForKey=0;
    public static boolean belirtec=false;
    public static boolean sisOlustur=false;
    int bulamazsaSinirlandirmaKontrolü=0;
     public static int rowCount=satirSutunKontrol.satir ; // Satır sayısı
    public static int  columnCount =satirSutunKontrol.sutun; // Sütun sayısı
///sonradan eklenenler
 Nodes[][] node=new Nodes[rowCount][columnCount];
 Nodes startNode,currentNode,startsNode;
 
   
   Nodes current;
int giris=0;
 ArrayList<Nodes> openList = new ArrayList();

 ArrayList<Nodes> checkedList = new ArrayList();//array listin dizisi bu şekilde tutuluyorrrr
 int toplamGiris=0;
 int kacTaneHedefVar=8;////////////////////////////////////////*********************hedef sayisini burada belirtmem lazımmmm
  //    kacTaneHedefVar=Node.goal.length;//sıkıntı çıkarsa bak buraya bir daha goalı dizi şeklinde yazmayı da deneyebilirsin
   Boolean goalReached[]=new Boolean[kacTaneHedefVar];
    int hedefleriTut[][]=new int[rowCount][columnCount];
    Nodes goalNode[]=new Nodes[kacTaneHedefVar];//yine sonradan// normalde 2 idi//4 idi çok güzzel çalışıyordu
  //   ArrayList<Nodes> bulunmayaniCikar [] = new ArrayList[kacTaneHedefVar];
    Nodes bulunmayaniCikar[]=new Nodes[kacTaneHedefVar];
      Rectangle robotRect;
    int isAnotherRowAndCollumnsAreZero;
    public static int naber;
    private BufferedImage backgroundImage;
    private JPanel   gridPanel;
   
    public static int cellSize ; // Hücre boyutu
       int startRow ;
        int startColumn ;
       public static boolean createFog=false;
    int treasureHunterX=0;
    int treasureHunterY=0;
 static   int rastX;
 static   int rastY;//staiclik sonradan eklendi
         int birdLocation;
     ArrayList<Integer> beeXCoordinats = new ArrayList<>();
     ArrayList<Integer> beeYCoordinats = new ArrayList<>();
//    public void setRowCount(int rowCount) {
//        this.rowCount = rowCount;
//    }
//
//    public void setColumnCount(int columnCount) {
//        this.columnCount = columnCount;
//    }
   public  static int[][] grid;//private ididiiii ve static değildiiii
    public static boolean[][] sisEfekti = new boolean[rowCount][columnCount];////// static ve public sonradan eklendi önceden private idi
    private int beeX, beeY; // Arının koordinatları
    private  Random random;
int countOfEnter=0;
    JScrollPane scrollPane;
    JScrollBar horizontalScrollBar;
    JScrollBar verticalScrollBar;
     int sBLocationHorizantal;
         int sBLocationVertical;
    public  ProLab() {
        for (int i = 0; i < kacTaneHedefVar; i++) {
            goalReached[i]=false;//yine sonradan gelmeler
        }
//     goalReached[0]=false;//yine sonradan gelmeler
//     goalReached[1]=false;//null hatası giderilmesi için yazıldı
//     goalReached[2]=false;//her yeni engel için yazılmalı sanırım ....
//     goalReached[3]=false;
        setTitle(":(");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGrid(g);///sisli kısım varmı yok mu onu da graw gridde belirtmem lazım 
              //  drawBackground(g);
            }
        };
          random = new Random();
          gridPanel.setPreferredSize(new Dimension(columnCount * cellSize, rowCount * cellSize));
 
        scrollPane = new JScrollPane(gridPanel); // JPanel'i JScrollPane içine yerleştir
      scrollPane.getVerticalScrollBar().setUnitIncrement(20);//scrollbarı hızlandırmak için 
        getContentPane().add(scrollPane); // JScrollPane'i pencereye ekle 
        horizontalScrollBar=scrollPane.getHorizontalScrollBar();
        verticalScrollBar=scrollPane.getVerticalScrollBar();
        generateGrid();
        for (int i = 0; i < kacTaneHedefVar; i++) {
             setCostOnNodes(i);
        }
//        setCostOnNodes(0);
//        setCostOnNodes(1);
//        setCostOnNodes(2);
//        setCostOnNodes(3);
        createFog(rowCount,columnCount);
        gridPanel.repaint();

     pack();
        setLocationRelativeTo(null);
        setVisible(true);
      //updateGrid();
      moveBee();// dinamik hareketi sağlayacak olan fonksiyon burada    
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);     

        
    }
     private void setStartNode(int row,int col){
     node[row][col].setAsStart();
     startNode=node[row][col];
     startsNode=node[row][col];           //// başlatmaaayı nasıl yapacağız  
     currentNode=startNode;
     
 }
 private void setGoalNode(int row,int col,int kacinciHedef){
     node[row][col].setAsGoal(kacinciHedef);
     goalNode[kacinciHedef]=node[row][col];// şuanki hedef olsun burası
     
 }
 private void setSolidNode(int row,int col){
     node[row][col].setAsSolid();
 
 }
 
 private void getCost(Nodes node,int kacinciHedef){ // tüm nodelara uygulamak için ayrı bir for döngüsü yapaccağım
        // get g cost
        switch (kacinciHedef) {
            case 1:
                startNode=goalNode[0];///bir hedefe gittikten sonra o hedefi başlangıç olarak alsın diye
                break;
            case 2:
                startNode=goalNode[1];
                break;
            case 3:
                startNode=goalNode[2];                    ///////buraları unutma değiştirmeyiiiiiiiiiiiiiiiiiiiii
                break;
                 case 4:////////////////16 mart eklendii altt tarafla birlikte
                startNode=goalNode[3];                    ///////buraları unutma değiştirmeyiiiiiiiiiiiiiiiiiiiii
                break;
                 case 5:
                startNode=goalNode[4];                    ///////buraları unutma değiştirmeyiiiiiiiiiiiiiiiiiiiii
                break;
                 case 6:
                startNode=goalNode[5];                    ///////buraları unutma değiştirmeyiiiiiiiiiiiiiiiiiiiii
                break;
                 case 7:
                startNode=goalNode[6];                    ///////buraları unutma değiştirmeyiiiiiiiiiiiiiiiiiiiii
                break;
            default:
                break;
        }
     
     int xDistance=Math.abs(node.col-startNode.col);
     int yDistance=Math.abs(node.row-startNode.row);
     node.gCost[kacinciHedef]=xDistance+yDistance;
     
     //get h cost
     xDistance=Math.abs(node.col-goalNode[kacinciHedef].col);
     yDistance=Math.abs(node.row-goalNode[kacinciHedef].row);
     node.hCost[kacinciHedef]=xDistance+yDistance;
     
     //get F cost 
     node.fCost[kacinciHedef]=node.gCost[kacinciHedef]+node.hCost[kacinciHedef];
     
     // ekranda maliyetleri gösterme /rmalde/2 1 idid n
//     if(kacinciHedef==3 && node!=startNode && node!=goalNode[kacinciHedef]){
//     //  node.setText("<html>F:"+node.fCost[kacinciHedef]+"F':"+node.fCost[kacinciHedef-1]+"+<br>G:"+node.gCost[kacinciHedef]+"G':"+node.gCost[kacinciHedef-1]+"<br>H:"+node.hCost[kacinciHedef]+"H':"+node.hCost[kacinciHedef-1]+"</html>");//hyml tarzında bir yazım yapabiliriz bu şekilde
//     }
      toplamGiris++;
 }
 private void setCostOnNodes(int kacinciHedef){
     for(int i=0;i<rowCount;i++){
         for(int j=0;j<columnCount;j++){
             getCost(node[i][j],kacinciHedef);//tüm nodelara uygulamak için n
         }
     }
 }


    

    private void generateGrid() {
        int rast;
        
        int choosedBarrier;
        int rockVariety;
        int treeVariety;
        
        grid = new int[rowCount][columnCount];
          
        // Grid'i beyaz (boş) olarak başlatma
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                grid[i][j] = 0;
                node[i][j]=new Nodes(i,j); //satır ve sütunları nodea aktardıdıımm //sıra engellere geldi 
            }
        }
        rastX=random.nextInt(rowCount);
        rastY=random.nextInt(columnCount);
        verticalScrollBar.setValue(rastX*32);
        horizontalScrollBar.setValue(rastY*32);// DÜNYANIN EN SAAÇMMAAAA HATASIIIIIIIIIIIII
        verticalScrollBar.setValue(rastX*32);
        int yet=rastY;
        System.out.println(yet+" nannnaanna");
        System.out.println(rastX+"vavvavaa");
        grid[rastX][rastY]=-1;
         node[rastX][rastY].nodeValue=-1;//for start node or minions
        startNode=node[rastX][rastY];
        hedefleriTut[rastX][rastY]=-1;//karakterin sonradan başlangıç yerinde gözükmesi için 
         startNode.start=true;//olmayadabilir önemli 
        currentNode=startNode;
         for (int i = 0; i <rowCount; i++) {
            for (int j = 0;j <columnCount; j++) {
               if(grid[i][j]==0 ){
                rast=random.nextInt(11);
                  if(rast==0){//The possibility of creating barrier = 1/11                
                     choosedBarrier=random.nextInt(7);// 3 vardı aşağı tarafdad
                   if( (j>4) && choosedBarrier==0 && (columnCount-j-6)>0 &&(rowCount-i-6)>0){// koyulacak engelin konumu belirlenirken matris dışına çıkılmaması için buralar yazılıyor
                       isAnotherRowAndCollumnsAreZero=0;
                       for(int a=i;a<i+2;a++){
                                for(int b=j;b<j+5;b++){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }
                       for(int a=i;a<i+2;a++){
                                for(int b=j;b>j-4;b--){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }
                            if(isAnotherRowAndCollumnsAreZero==18){
                                for(int a=i;a<i+2;a++){
                                for(int b=j;b<j+5;b++){
                                    if(a==i && b==j){
                                        grid[a][b]=Bees.realBarrierNumber;
                                         node[a][b].nodeValue=Bees.realBarrierNumber;
                                         node[a][b].solid=true;
                                        beeXCoordinats.add(b);
                                        beeYCoordinats.add(a);
                                    }
                                    else{  
                                        grid[a][b]=Bees.beeBarrierNumber;
                                         node[a][b].nodeValue=Bees.beeBarrierNumber;
                                         node[a][b].solid=true;
                                    }
                                }
                            }
                                for(int a=i;a<i+2;a++){
                                for(int b=j;b>j-4;b--){
                                   if(a==i && b==j){
                                        grid[a][b]=Bees.realBarrierNumber;
                                         node[a][b].nodeValue=Bees.realBarrierNumber;
                                         node[a][b].solid=true;
                                   }
                                    else{  
                                        grid[a][b]=Bees.beeBarrierNumber;
                                         node[a][b].nodeValue=Bees.beeBarrierNumber;
                                         node[a][b].solid=true;
                                    }
                                }
                            }
                                
                                grid[i][j+1]=65;
                                grid[i+1][j]=65;
                                grid[i+1][j+1]=65;
                                 node[i][j+1].nodeValue=65;
                                  node[i+1][j].nodeValue=65;
                                   node[i+1][j+1].nodeValue=65;
                            }
                   }////// // this part about birds how to fly // alttaki 6 lılar gereksiz olabilir
                   else if( (i>4) && choosedBarrier==1 && (columnCount-j-6)>0 &&(rowCount-i-6)>0){// koyulacak engelin konumu belirlenirken matris dışına çıkılmaması için buralar yazılıyor
                       isAnotherRowAndCollumnsAreZero=0;
                       for(int a=i;a<i+5;a++){
                                for(int b=j;b<j+2;b++){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }//2 more square from to 2 much using bird
                       for(int a=i;a>i-4;a--){
                                for(int b=j;b<j+2;b++){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }
                            if(isAnotherRowAndCollumnsAreZero==18){
                                for(int a=i;a<i+5;a++){
                                for(int b=j;b<j+2;b++){// burası dahil düzellttim
                                    if(a==i && b==j){
                                        grid[a][b]=Birds.birdBarrierNumber;//5
                                         node[a][b].nodeValue=Birds.birdBarrierNumber;
                                         node[a][b].solid=true;
                                         
                                         node[a][b+1].solid=true;
                                         node[a+1][b].solid=true;
                                         node[a+1][b+1].solid=true;
                                        
                                         node[a][b+1].nodeValue=55;
                                         node[a+1][b].nodeValue=55;
                                         node[a+1][b+1].nodeValue=55;
                                      
                                         grid[a][b+1]=55;//0 harici olsun ama  herhangi bir şey de çizdirilmesin ekrana mantığıyla
                                         grid[a+1][b]=55;
                                         grid[a+1][b+1]=55;
                                     
                                     //   beeXCoordinats.add(b);//buların etkisi yok büyük ihtimalle
                                     //   beeYCoordinats.add(a);
                                    }
                                    else{  
                                       if(grid[a][b]!=55){
                                        grid[a][b]=Birds.upDownNumber;
                                         node[a][b].nodeValue=Birds.upDownNumber;
                                         node[a][b].solid=true;
                                       }
                                    }
                                }
                            }
                                for(int a=i;a>i-4;a--){
                                for(int b=j;b<j+2;b++){
                                   if(a==i && b==j){
                                        grid[a][b]=Birds.birdBarrierNumber;
                                         node[a][b].nodeValue=Birds.birdBarrierNumber;
                                         node[a][b].solid=true;
                                         
                                         node[a][b+1].solid=true;
                                         node[a+1][b].solid=true;
                                         node[a+1][b+1].solid=true;
                                         
                                         node[a][b+1].nodeValue=55;
                                         node[a+1][b].nodeValue=55;
                                         node[a+1][b+1].nodeValue=55;
                                    
                                         grid[a][b+1]=55;//0 harici olsun ama  herhangi bir şey de çizdirilmesin ekrana mantığıyla
                                         grid[a+1][b]=55;
                                         grid[a+1][b+1]=55;
                                     }
                                    else{
                                       if(grid[a][b]!=55){
                                        grid[a][b]=Birds.upDownNumber;
                                        node[a][b].nodeValue=Birds.upDownNumber;
                                        node[a][b].solid=true;
                                       }
                                    }
                                }
                               
                                
                            }
                              
                            }
                   }
                                       //Rocksss
                    else if( choosedBarrier==2&&(columnCount-j-4)>0&&(rowCount-i-4)>0){
                       isAnotherRowAndCollumnsAreZero=0;
                        rockVariety=random.nextInt(2);
                        if(rockVariety==0){
                            for(int a=i;a<i+2;a++){
                                for(int b=j;b<j+2;b++){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }
                            if(isAnotherRowAndCollumnsAreZero==4){
                                for(int a=i;a<i+2;a++){
                                for(int b=j;b<j+2;b++){
                                         grid[a][b]=Rocks.rockBarrierNumber;
                                         node[a][b].nodeValue=Rocks.rockBarrierNumber;
                                         node[a][b].solid=true;
                                }
                            }
                            }
                       
                        }
                        else{
                            for(int a=i;a<i+3;a++){
                                for(int b=j;b<j+3;b++){
                                    if(grid[a][b]==0){
                                    isAnotherRowAndCollumnsAreZero++;
                                    }
                                }
                            }
                            if(isAnotherRowAndCollumnsAreZero==9){
                                for(int a=i;a<i+3;a++){
                                for(int b=j;b<j+3;b++){
                                         grid[a][b]=Rocks.rockBarrierNumber;
                                         node[a][b].nodeValue=Rocks.rockBarrierNumber;
                                         node[a][b].solid=true; 
                                }
                            }
                            }                   
                        }
                        }/// Treeeeee
                    else if( choosedBarrier==3&&(columnCount-j-7)>0&&(rowCount-i-7)>0){
                        treeVariety=random.nextInt(4);
                       isAnotherRowAndCollumnsAreZero=0;
                        switch (treeVariety) {
                           case 0:
                                for(int a=i;a<i+2;a++){
                                   for( int b=j;b<j+2;b++){
                                       if(grid[a][b]==0){
                                           isAnotherRowAndCollumnsAreZero++;
                                       }
                                    //   grid[a][b]=Trees.treeBarrierNumber;
                                   }
                               }
                                if(isAnotherRowAndCollumnsAreZero==4){
                                for(int a=i;a<i+2;a++){
                                   for( int b=j;b<j+2;b++){
                                         
                                       grid[a][b]=Trees.treeBarrierNumber;
                                       node[a][b].nodeValue=Trees.treeBarrierNumber;
                                       node[a][b].solid=true;
                                   
                                   }
                               }
                             }
                               break;
                           case 1:
                                for(int a=i;a<i+3;a++){
                                   for( int b=j;b<j+3;b++){
                                       if(grid[a][b]==0){
                                           isAnotherRowAndCollumnsAreZero++;
                                       }
                                      // grid[a][b]=Trees.treeBarrierNumber;
                                   }
                               }
                                if(isAnotherRowAndCollumnsAreZero==9){
                                for(int a=i;a<i+3;a++){
                                   for( int b=j;b<j+3;b++){               
                                       grid[a][b]=Trees.treeBarrierNumber;
                                       node[a][b].nodeValue=Trees.treeBarrierNumber;
                                       node[a][b].solid=true;

                                   }
                               }
                             }
                               break;
                           case 2:
                                for(int a=i;a<i+4;a++){
                                   for( int b=j;b<j+4;b++){
                                       if(grid[a][b]==0){
                                           isAnotherRowAndCollumnsAreZero++;
                                       }
                                      // grid[a][b]=Trees.treeBarrierNumber;
                                   }
                               }
                                if(isAnotherRowAndCollumnsAreZero==16){
                                for(int a=i;a<i+4;a++){
                                   for( int b=j;b<j+4;b++){               
                                       grid[a][b]=Trees.treeBarrierNumber;
                                       node[a][b].nodeValue=Trees.treeBarrierNumber;
                                       node[a][b].solid=true;
                                   }
                               }
                             }
                                        
                               break;
                           case 3:
                            
                              for(int a=i;a<i+5;a++){
                                   for( int b=j;b<j+5;b++){
                                       if(grid[a][b]==0){
                                           isAnotherRowAndCollumnsAreZero++;
                                       }
                                      // grid[a][b]=Trees.treeBarrierNumber;
                                   }
                               }
                                if(isAnotherRowAndCollumnsAreZero==25){
                                for(int a=i;a<i+5;a++){
                                   for( int b=j;b<j+5;b++){               
                                       grid[a][b]=Trees.treeBarrierNumber;
                                       node[a][b].nodeValue=Trees.treeBarrierNumber;
                                       node[a][b].solid=true;
                                   }
                               }
                             }
                                       
                               break;
                               
                           default:
                               System.out.println("beklenmeyen tree hatasi");
                               //throw new AssertionError();
                       }
                      
                   }
                    else if(choosedBarrier==4&&(columnCount-j-16)>0&&(rowCount-i-16)>0){                                    //Mountains
                        int isCreateMountain=random.nextInt(5);
                        int isAnotherRowAndCollumnsAreZero=0;
                         if(isCreateMountain==1){      
                        for( int a=i;a<i+15;a++){
                                   for( int b=j;b<j+15;b++){
                                       if(grid[a][b]==0){
                                           isAnotherRowAndCollumnsAreZero++;//diğer karelerde de 0 var ise o zman engeli yerleştir
                                       }
                                      //   grid[a][b]=Mountains.mountainBarrierNumber;
                                   }
                               }
                        if(isAnotherRowAndCollumnsAreZero==225){
                        for( int a=i;a<i+15;a++){
                                   for( int b=j;b<j+15;b++){                                
                                         grid[a][b]=Mountains.mountainBarrierNumber;
                                         node[a][b].nodeValue=Mountains.mountainBarrierNumber;
                                         node[a][b].solid=true;
                                   }
                               }
                         }
                      }
                   }
                    else if(choosedBarrier==5&&(columnCount-j-10)>0 && (j!=0) && (grid[i][j-1]!=Walls.wallBarrierNumber)){    // Walls
                         isAnotherRowAndCollumnsAreZero=0;
                            for(int b=j;b<j+10;b++){
                            if(grid[i][b]==0){
                                isAnotherRowAndCollumnsAreZero++;
                            }
                           //  grid[i][b]=Walls.wallBarrierNumber;
                        }   
                            if(isAnotherRowAndCollumnsAreZero==10){
                               for(int b=j;b<j+10;b++){
                               grid[i][b]=Walls.wallBarrierNumber;
                               node[i][b].nodeValue=Walls.wallBarrierNumber;
                               node[i][b].solid=true;
                               }
                            }
                   }
                   
                    //else de grid[i][j}=0 yapiyordum
                  }
               }
                System.out.print(grid[i][j]);
            }
             System.out.println();
        }
          // for (int i = 0; i <rowCount; i++) {
           // for (int j = 0;j <columnCount; j++) {
             int randomRow;
             int randomColumn;
             boolean goldenIsPlaced=false;
             boolean silverIsPlaced=false;
             boolean copperIsPlaced=false;
             boolean emeraldIsPlaced=false;
             boolean goldenIsPlaced1=false;
             boolean silverIsPlaced1=false;
             boolean copperIsPlaced1=false;
             boolean emeraldIsPlaced1=false;
             int sayiyaUlasildimi=0;
             while(sayiyaUlasildimi!=8){//dikktatli oll//emeralIsPlaced1==false var ididi wwhile içinde
                 randomRow   =  random.nextInt(rowCount);
                 randomColumn=  random.nextInt(columnCount);  
                if(grid[randomRow][randomColumn]==0){
                    if(goldenIsPlaced==false){
                        grid[randomRow][randomColumn]=7;
                        node[randomRow][randomColumn].nodeValue=7;
                    //   goalNode[0]=node[randomRow][randomColumn];   
                        goldenIsPlaced=true;
                        rowGolden=randomRow;
                        colGolden=randomColumn;
                         hedefleriTut[randomRow][randomColumn]=7;
                         sayiyaUlasildimi++;
                    }
                    else if(silverIsPlaced==false){
                        grid[randomRow][randomColumn]=8;
                        node[randomRow][randomColumn].nodeValue=8;
                    //   goalNode[1]=node[randomRow][randomColumn];   
                        silverIsPlaced=true;
                        rowSilver=randomRow;
                        colSilver=randomColumn;
                         hedefleriTut[randomRow][randomColumn]=8;
                         sayiyaUlasildimi++;
                
                    }
                    else if(copperIsPlaced==false){
                        grid[randomRow][randomColumn]=9;
                        node[randomRow][randomColumn].nodeValue=9;
                    //    goalNode[2]=node[randomRow][randomColumn];   
                        copperIsPlaced=true;
                        rowCopper=randomRow;
                        colCopper=randomColumn;
                        hedefleriTut[randomRow][randomColumn]=9;
                        sayiyaUlasildimi++;
                    }
                    else if(emeraldIsPlaced==false){
                        grid[randomRow][randomColumn]=10;
                        node[randomRow][randomColumn].nodeValue=10;
                    //  goalNode[3]=node[randomRow][randomColumn];   
                        emeraldIsPlaced=true;
                        rowEmerald=randomRow;
                        colEmerald=randomColumn;
                        hedefleriTut[randomRow][randomColumn]=10;
                        sayiyaUlasildimi++;
                    }
                    else if(goldenIsPlaced1==false){
                        grid[randomRow][randomColumn]=7;
                        node[randomRow][randomColumn].nodeValue=7;
                    //   goalNode[0]=node[randomRow][randomColumn];   
                        goldenIsPlaced1=true;
                        rowGolden1=randomRow;
                        colGolden1=randomColumn;
                         hedefleriTut[randomRow][randomColumn]=7;
                         sayiyaUlasildimi++;
                    }
                    else if(silverIsPlaced1==false){
                        grid[randomRow][randomColumn]=8;
                        node[randomRow][randomColumn].nodeValue=8;
                    //   goalNode[1]=node[randomRow][randomColumn];   
                        silverIsPlaced1=true;
                        rowSilver1=randomRow;
                        colSilver1=randomColumn;
                         hedefleriTut[randomRow][randomColumn]=8;
                         sayiyaUlasildimi++;
                
                    }
                    else if(copperIsPlaced1==false){
                        grid[randomRow][randomColumn]=9;
                        node[randomRow][randomColumn].nodeValue=9;
                    //    goalNode[2]=node[randomRow][randomColumn];   
                        copperIsPlaced1=true;
                        rowCopper1=randomRow;
                        colCopper1=randomColumn;
                        hedefleriTut[randomRow][randomColumn]=9;
                        sayiyaUlasildimi++;
                    }
                    else if(emeraldIsPlaced1==false){
                        grid[randomRow][randomColumn]=10;
                        node[randomRow][randomColumn].nodeValue=10;
                    //  goalNode[3]=node[randomRow][randomColumn];   
                        emeraldIsPlaced1=true;
                        rowEmerald1=randomRow;
                        colEmerald1=randomColumn;
                        hedefleriTut[randomRow][randomColumn]=10;
                        sayiyaUlasildimi++;
                    }
                    
                }
            }
        //     if(emeraldIsPlaced1==true){
               int uzaklikBaslangicaDikeyGolden=Math.abs(startNode.row-rowGolden);
               int uzaklikBaslangicaYatayGolden=Math.abs(startNode.col-colGolden);
               int uzaklikGolden=uzaklikBaslangicaDikeyGolden+uzaklikBaslangicaYatayGolden;
               
               int uzaklikBaslangicaDikeySilver=Math.abs(startNode.row-rowSilver);
               int uzaklikBaslangicaYataySilver=Math.abs(startNode.col-colSilver);
               int uzaklikSilver=uzaklikBaslangicaDikeySilver+uzaklikBaslangicaYataySilver;
               
               int uzaklikBaslangicaDikeyCopper=Math.abs(startNode.row-rowCopper);
               int uzaklikBaslangicaYatayCopper=Math.abs(startNode.col-colCopper);
               int uzaklikCopper=uzaklikBaslangicaDikeyCopper+uzaklikBaslangicaYatayCopper;
               
               int uzaklikBaslangicaDikeyEmerald=Math.abs(startNode.row-rowEmerald);
               int uzaklikBaslangicaYatayEmerald=Math.abs(startNode.col-colEmerald);
               int uzaklikEmerald=uzaklikBaslangicaDikeyEmerald+uzaklikBaslangicaYatayEmerald;
               //  int uzaklikSiralama[]= {uzaklikGolden,uzaklikSilver,uzaklikCopper,uzaklikEmerald};
               
               int uzaklikBaslangicaDikeyGolden1=Math.abs(startNode.row-rowGolden1);
               int uzaklikBaslangicaYatayGolden1=Math.abs(startNode.col-colGolden1);
               int uzaklikGolden1=uzaklikBaslangicaDikeyGolden1+uzaklikBaslangicaYatayGolden1;
               
               int uzaklikBaslangicaDikeySilver1=Math.abs(startNode.row-rowSilver1);
               int uzaklikBaslangicaYataySilver1=Math.abs(startNode.col-colSilver1);
               int uzaklikSilver1=uzaklikBaslangicaDikeySilver1+uzaklikBaslangicaYataySilver1;
               
               int uzaklikBaslangicaDikeyCopper1=Math.abs(startNode.row-rowCopper1);
               int uzaklikBaslangicaYatayCopper1=Math.abs(startNode.col-colCopper1);
               int uzaklikCopper1=uzaklikBaslangicaDikeyCopper1+uzaklikBaslangicaYatayCopper1;
               
               int uzaklikBaslangicaDikeyEmerald1=Math.abs(startNode.row-rowEmerald1);
               int uzaklikBaslangicaYatayEmerald1=Math.abs(startNode.col-colEmerald1);
               int uzaklikEmerald1=uzaklikBaslangicaDikeyEmerald1+uzaklikBaslangicaYatayEmerald1;
        int uzaklikSiralama[]= {uzaklikGolden,uzaklikSilver,uzaklikCopper,uzaklikEmerald,uzaklikGolden1,uzaklikSilver1,uzaklikCopper1,uzaklikEmerald1};
               Arrays.sort (uzaklikSiralama);
                 for (int i = 0; i < kacTaneHedefVar; i++) {/////////////// uzaklara göre hedef belirleme işlemi yapıldı
                        
                 if (uzaklikSiralama[i]==uzaklikGolden) {
                      goalNode[i]=node[rowGolden][colGolden];
                    
                 }
                 else if (uzaklikSiralama[i]==uzaklikSilver) {
                      goalNode[i]=node[rowSilver][colSilver];
                 }
                 else if (uzaklikSiralama[i]==uzaklikCopper) {
                      goalNode[i]=node[rowCopper][colCopper];
                 }
                 else if (uzaklikSiralama[i]==uzaklikEmerald) {
                      goalNode[i]=node[rowEmerald][colEmerald];
                 }
                 else if (uzaklikSiralama[i]==uzaklikGolden1) {
                      goalNode[i]=node[rowGolden1][colGolden1];
                    
                 }
                 else if (uzaklikSiralama[i]==uzaklikSilver1) {
                      goalNode[i]=node[rowSilver1][colSilver1];
                 }
                 else if (uzaklikSiralama[i]==uzaklikCopper1) {
                      goalNode[i]=node[rowCopper1][colCopper1];
                 }
                 else if (uzaklikSiralama[i]==uzaklikEmerald1) {
                      goalNode[i]=node[rowEmerald1][colEmerald1];
                 }
                 
             } 
                     
        //}
         
          gridPanel.repaint();
    }

     public static void createFog(int rowCount,int columnCount) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
            
                sisEfekti[i][j] = sisOlustur;/////////////////////////////güncelleme gelecek
              
            }
        }

    }
    
    
       //minions move atractions are here
       private void movePlayer(int dx, int dy) {
        
        if((rastX+dx)<rowCount &&(rastY+dy)<columnCount &&(rastY+dy)>=0 &&(rastX+dx)>=0 ){
            if(grid[rastX+dx][rastY+dy]==0||grid[rastX+dx][rastY+dy]==7||grid[rastX+dx][rastY+dy]==8||grid[rastX+dx][rastY+dy]==9||grid[rastX+dx][rastY+dy]==10)
            { 
               
                scrollIfNeeded(dy, dx);
               // scrollIfNeeded(rastX,rastY);// for the automatic action to scroll bar 
            grid[rastX][rastY]=0;
        int newPlayerX = rastX+dx;
        int newPlayerY = rastY+dy;
        for (int i =newPlayerX-3; i <=newPlayerX+3 ; i++) {
           
            for (int j = newPlayerY-3; j <=newPlayerY+3 ; j++) {  //guruurr
               if(i<rowCount && i>=0 && j<columnCount && j>=0){
                   sisEfekti[i][j]=false;
                   
               }  

                
               }
        }
        rastX = newPlayerX;
        rastY = newPlayerY;
        grid[rastX][rastY]=Minion.minionNumber;
        gridPanel.repaint();
      // updateGrid();
            }
        }
    }
    
       private void openNode(Nodes node,int kacinciHedef){///sorun burada değil open node un geldiği yerdeymiş meğer
// System.out.println(kacinciHedef+".hedefe giden yol satir: "+node.row+" sutun: "+node.col);     
//kontrol edilmemişse açık değilse açmaya çalışıcaz ve arada engel bulunmamması lazım 
     if(node.open==false && node.checked==false && node.solid==false){///her şey buradada bitiyorrr
         node.setAsOpen();//
         System.out.println(kacinciHedef+".hedefe giden yol satir: "+currentNode.row+" sutun: "+currentNode.col);
    //     node.parent[kacinciHedef]=currentNode;// her birinin nerden geldiğini kontrol edebiliyoruz bu şekkilde
           node.parent[kacinciHedef]=currentNode;
         openList.add(node);
         for (int i =node.row-3; i <=node.row+3 ; i++) {
           
            for (int j = node.col-3; j <=node.col+3 ; j++) {  //guruurr
               if(i<rowCount && i>=0 && j<columnCount && j>=0){
                   sisEfekti[i][j]=false;//yeni eklendi buralar hadi hayırlıısı bakalım 
                   
                }  

                
            }
        }
               gridPanel.repaint();  //h 5: 27 d 14 
         
     }
     
 }
          private void closeNode(Nodes node,int kacinciHedef){///sorun burada değil open node un geldiği yerdeymiş meğer
// System.out.println(kacinciHedef+".hedefe giden yol satir: "+node.row+" sutun: "+node.col);     
//kontrol edilmemişse açık değilse açmaya çalışıcaz ve arada engel bulunmamması lazım 
     if(node.open==true && node.checked==true && node.solid==false){///her şey buradada bitiyorrr
         node.setAsClose();//
       //  System.out.println(kacinciHedef+".hedefe giden yol satir: "+currentNode.row+" sutun: "+currentNode.col);
    //     node.parent[kacinciHedef]=currentNode;// her birinin nerden geldiğini kontrol edebiliyoruz bu şekkilde
     //      node.parent[kacinciHedef]=currentNode;
       node.parent[kacinciHedef]=null;
     openList.remove(node);//add var idi
         for (int i =node.row-3; i <=node.row+3 ; i++) {
           
            for (int j = node.col-3; j <=node.col+3 ; j++) {  //guruurr
               
                if(i<rowCount && i>=0 && j<columnCount && j>=0) {
                   sisEfekti[i][j]=true;//yeni eklendi buralar hadi hayırlıısı bakalım 
                   
                }  

                
            }
        }
               gridPanel.repaint();  //h 5: 27 d 14 
         
     }
     
 }
 
       public int searchs(int kacinciHedef){
           for (int i = 0; i < rowCount; i++) {
               for (int j = 0; j < columnCount; j++) {
                   System.out.print(" "+grid[i][j]+" ");
               }
           System.out.println();
           }
           
      System.out.println(kacinciHedef+"  ssss");
     if(kacinciHedef<kacTaneHedefVar&& goalReached[kacinciHedef]==false && bulamazsaSinirlandirmaKontrolü<200){
         int col =currentNode.col;
         int row=currentNode.row;
         System.out.println(kacinciHedef);
         currentNode.setAsChecked(kacinciHedef,row,col);//////////////////////////////////////////bir daha bakılması lazım  //0 var ididiii
         checkedList.add(currentNode);
         openList.remove(currentNode);
                 if(col-1>=0){
         openNode(node[row][col-1],kacinciHedef);
         }
         if(col+1<columnCount){
         openNode(node[row][col+1],kacinciHedef);
         }
         if(row-1>=0){
         openNode(node[row-1][col],kacinciHedef);
         }
         if(row+1<rowCount){
         openNode(node[row+1][col],kacinciHedef);
         }
         
         // en iyiyi node u buuyoruz
         int bestNodeIndex=0; //her seferinde yeni açık node lar arasındaa karşılaştır
         int bestNodefCost =999;
         for(int i=0;i<openList.size();i++){
             if(openList.get(i).fCost[kacinciHedef]<bestNodefCost){
                 bestNodeIndex=i;
                 bestNodefCost=openList.get(i).fCost[kacinciHedef];
             }
             else if(openList.get(i).fCost[kacinciHedef]==bestNodefCost){
                 if(openList.get(i).gCost[kacinciHedef]<openList.get(bestNodeIndex).gCost[kacinciHedef]){
                     bestNodeIndex=i;                   
                 }
                 
                 
             }
         }
         if(!openList.isEmpty()){
             
             currentNode=openList.get(bestNodeIndex);///null hatası alıyorum belki çözerrr
             System.out.println("open list dolu");
         //}
         
         if(currentNode==goalNode[kacinciHedef]){ ///hedefi bulduğum zaman yapacaklarım buradaaaaaaaaaaa
             Nodes.goal[kacinciHedef]=true;//hedeflerin boyanmaması için eklendi
             System.out.println("hedef bulundu ------------");
             goalReached[kacinciHedef]=true;
             if (kacinciHedef<kacTaneHedefVar) {///yeni güncelleme geldi
              //   kacinciHedef++;// önceki konnumm
                 if (kacinciHedef==kacTaneHedefVar-1) {
                     traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);
                  return -1;
                 }
                // traceBackThePath(goalNode[kacinciHedef],kacinciHedef);//aslında sadece else de var ididd
//                 
                 else{
                     kacinciHedef++;//bismillahirrahmanirrahim
                     System.out.println("artttirilmissss Hedef : "+kacinciHedef);
                     switch (kacinciHedef) {
                         case 1:
                             return 1;
                         case 2:
                             System.out.println("case de gerceklesti");
                             return 2;
                         case 3:
                             return 3;
                         default:
                             throw new AssertionError();
                     }
                // searchs(kacinciHedef);
                         }
             }
             else{
                  //searchs(kacinciHedef);
                 //traceBackThePath(goalNode[kacinciHedef],kacinciHedef);
             //traceBackThePath(goalNode[0],kacinciHedef);// bi kontrol etttttttttttt
           //  traceBackThePath();
             }
         }
         }//aslında bu yoktu içimden geldi
         else{
             
             System.out.println("bulamadi***************************************");
            bulamazsaSinirlandirmaKontrolü=300;
           
           //   traceBackThePath(goalNode[0],kacinciHedef);/////buradaki else kısmı çok sonradan yazıldı siline de bilir 
            
         }
         bulamazsaSinirlandirmaKontrolü++;
     }
 return 0;
   }

   private void traceBackTheAllPath(Nodes oncekiBaslangicNode,int kacinciHedef){

     System.out.println(kacinciHedef);
    
  
      System.out.println("tracebackegirdi----------");
         current=goalNode[7];// 3 var idi son hedefe indeksle
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
              System.out.println("koordinat son yol satir: "+current.row+"sutun: "+current.col);
              current=current.parent[7];
             
           //   System.out.println("current  null degilmiss***********************");
       
         if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]&& current!=goalNode[3]&& current!=goalNode[4]&& current!=goalNode[5]&& current!=goalNode[6]&& current!=goalNode[7]) {
             int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
         }
            
         
     }
        if (current==null) {
                current=goalNode[6];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[6];
       //  System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
          int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
        if (current==null) {
                current=goalNode[5];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[5];
       //  System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
          int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
        if (current==null) {
                current=goalNode[4];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[4];
//         System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
          int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
        if (current==null) {
                current=goalNode[3];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[3];
//         System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
          int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
        if (current==null) {
                current=goalNode[2];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[2];
//         System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
          int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
    if (current==null) {
                current=goalNode[1];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[1];
//         System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) { 
           int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
    if (current==null) {
                current=goalNode[0];
        while (current!=null && current!=startsNode) {// öncekine doğru bir gidiş var başlangıç noktasına gelene kaddarki parentları yeşile boyama yapıcazz
         
              current=current.parent[0];
//         System.out.println("current  null degilmiss***********************");
       
      if (current!=null && current != startsNode && current!=goalNode[0]&& current!=goalNode[1]&& current!=goalNode[2]) {       
           int currentRow,currentCol=0;
             currentRow=current.row;
             currentCol=current.col;
             current.setAsPath(currentRow,currentCol);
             
                }
            
         
        } 
    }
             for (int i = 0; i < rowCount; i++) {//oldukçayeni heeflerdeki görsellerin sonradan eklenmesi ve gözükmesi için 
                 for (int j = 0; j < columnCount; j++) {
                     if(hedefleriTut[i][j]!=0){
                         grid[i][j]=hedefleriTut[i][j];
                         
                     }
                 }
             }
     
     
     
 }
    //Algoritma eklenecek burayaaaaaaaaaaaa
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                movePlayer(-1, 0);
                System.out.println("UP");
                break;
            case KeyEvent.VK_DOWN:
                movePlayer(1, 0);
                  System.out.println("DOWN");
                break;
            case KeyEvent.VK_LEFT:
                movePlayer(0, -1);
                  System.out.println("LEFT");
                break;
            case KeyEvent.VK_RIGHT:
                movePlayer(0, +1);
                System.out.println("RIGHT");
                break;
            case KeyEvent.VK_SHIFT:
                               //movePlayer(0, +1);
                 autoSearch(kacinciHedefForKey);
                
                
                break;
                
                case KeyEvent.VK_ENTER:
                //movePlayer(0, +1);
                  switch (kacinciHedefForKey) {
                case 0:
                    repaint();
                   if( searchs(kacinciHedefForKey)==1){
                       kacinciHedefForKey++;
                       repaint();
                   };
                    break;
                    case 1:
                   if( searchs(kacinciHedefForKey)==2){
                       kacinciHedefForKey++;
                   };
                    break;
                    case 2:
                   if( searchs(kacinciHedefForKey)==3){
                       kacinciHedefForKey++;
                   };
                    break;
                    case 3:
                   searchs(kacinciHedefForKey);
                    break;
                default:
                    throw new AssertionError();
            }
                System.out.println("------------------------------Algoritmaya giris----------------------------");
                break;
        }
    }
    
    int isITFirst=0;
     private void scrollIfNeeded(int horizontalRise,int verticalRise ) {

               sBLocationHorizantal=  horizontalScrollBar.getValue();
               sBLocationVertical=  verticalScrollBar.getValue();
               horizontalScrollBar.setValue(sBLocationHorizantal+(horizontalRise*30));//nereye giderse gitsin robotu takip edicek scroll barlar
               verticalScrollBar.setValue(sBLocationVertical+(verticalRise*30));
               
     
    }
     
      public int autoSearch(int kacinciHedef){
//           for (int i = 0; i < rowCount; i++) {
//               for (int j = 0; j < columnCount; j++) {
//                   System.out.print(" "+grid[i][j]+" ");
//               }
//           System.out.println();
//           }
           while(true){
               if ( bulunanHedefler.size()==kacTaneHedefVar || kacinciHedef==8) {
                   break;
               }
      System.out.println(kacinciHedef+"  ssss");
     if(kacinciHedef<kacTaneHedefVar&& goalReached[kacinciHedef]==false && bulamazsaSinirlandirmaKontrolü<10000){//while var idi
         int col =currentNode.col;
         int row=currentNode.row;
         System.out.println(kacinciHedef);
         currentNode.setAsChecked(kacinciHedef,row,col);//////////////////////////////////////////bir daha bakılması lazım  //0 var ididiii
         checkedList.add(currentNode);
         openList.remove(currentNode);
                 if(col-1>=0){
         openNode(node[row][col-1],kacinciHedef);
         if(node[row][col-1].solid==true || grid[row][col-1]==7|| grid[row][col-1]==8|| grid[row][col-1]==9|| grid[row][col-1]==10){
             int engelCesidi=grid[row][col-1];
             switch (engelCesidi) {
                 case 1 :
                   listeBulunanlar.add("Ağaç keşfedildi("+row+","+col--+")");
                     break;
                      case 2 :
                     listeBulunanlar.add("Kaya keşfedildi("+row+","+col--+")");
                     break;
                      case 3 :
                     listeBulunanlar.add("Duvar keşfedildi("+row+","+col--+")");
                     break;
                      case 4 :
                     listeBulunanlar.add("Dağ keşfedildi("+row+","+col--+")");
                     break;
                      case 5 :
                     listeBulunanlar.add("Kuş keşfedildi("+row+","+col--+")");
                     break;
                      case 6 :
                     listeBulunanlar.add("Arı keşfedildi("+row+","+col--+")");
                     break;
                   case 7 :
                     listeBulunanlar.add("Altın Sandık keşfedildi ("+row+","+col--+")");
                     listeBulunanHazineler.add("Altın Sandık keşfedildi("+row+","+col--+")");
                     break;
                      case 8 :
                     listeBulunanlar.add("Gümüş Sandık  keşfedildi("+row+","+col--+")");
                     listeBulunanHazineler.add("Gümüş Sandık keşfedildi("+row+","+col--+")");
                     break;
                      case 9 :
                     listeBulunanlar.add("Bakır Sandık keşfedildi("+row+","+col--+")");
                     listeBulunanHazineler.add("Bakır Sandık keşfedildi("+row+","+col--+")");
                     break;
                      case 10 :
                     listeBulunanlar.add("Zümrüt Sandık  keşfedildi("+row+","+col--+")");
                     listeBulunanHazineler.add("Zümrüt Sandık keşfedildi("+row+","+col--+")");
                     break;
             }
         }
             
         }
         if(col+1<columnCount){
         openNode(node[row][col+1],kacinciHedef);
                  if(node[row][col+1].solid==true || grid[row][col+1]==7|| grid[row][col+1]==8|| grid[row][col+1]==9|| grid[row][col+1]==10){
             int engelCesidi=grid[row][col+1];
            switch (engelCesidi) {
                 case 1 :
                   listeBulunanlar.add("Ağaç keşfedildi("+row+","+col+++")");
                     break;
                      case 2 :
                     listeBulunanlar.add("Kaya keşfedildi("+row+","+col+++")");
                     break;
                      case 3 :
                     listeBulunanlar.add("Duvar keşfedildi("+row+","+col+++")");
                     break;
                      case 4 :
                     listeBulunanlar.add("Dağ keşfedildi("+row+","+col+++")");
                     break;
                      case 5 :
                     listeBulunanlar.add("Kuş keşfedildi("+row+","+col+++")");
                     break;
                      case 6 :
                     listeBulunanlar.add("Arı keşfedildi("+row+","+col+++")");
                     break;
                   case 7 :
                     listeBulunanlar.add("Altın Sandık keşfedildi ("+row+","+col+++")");
                     listeBulunanHazineler.add("Altın Sandık keşfedildi("+row+","+col+++")");
                     break;
                      case 8 :
                     listeBulunanlar.add("Gümüş Sandık  keşfedildi("+row+","+col+++")");
                     listeBulunanHazineler.add("Gümüş Sandık keşfedildi("+row+","+col+++")");
                     break;
                      case 9 :
                     listeBulunanlar.add("Bakır Sandık keşfedildi("+row+","+col+++")");
                     listeBulunanHazineler.add("Bakır Sandık keşfedildi("+row+","+col+++")");
                     break;
                      case 10 :
                     listeBulunanlar.add("Zümrüt Sandık  keşfedildi("+row+","+col+++")");
                     listeBulunanHazineler.add("Zümrüt Sandık keşfedildi("+row+","+col+++")");
                     break;
             }
         }
         }
         if(row-1>=0){
         openNode(node[row-1][col],kacinciHedef);
            if(node[row-1][col].solid==true || grid[row-1][col]==7|| grid[row-1][col]==8|| grid[row-1][col]==9|| grid[row-1][col]==10){
             int engelCesidi=grid[row-1][col];
            switch (engelCesidi) {
                 case 1 :
                   listeBulunanlar.add("Ağaç keşfedildi("+row--+","+col+")");
                     break;
                      case 2 :
                     listeBulunanlar.add("Kaya keşfedildi("+row--+","+col+")");
                     break;
                      case 3 :
                     listeBulunanlar.add("Duvar keşfedildi("+row--+","+col+")");
                     break;
                      case 4 :
                     listeBulunanlar.add("Dağ keşfedildi("+row--+","+col+")");
                     break;
                      case 5 :
                     listeBulunanlar.add("Kuş keşfedildi("+row--+","+col+")");
                     break;
                      case 6 :
                     listeBulunanlar.add("Arı keşfedildi("+row--+","+col+")");
                     break;
                   case 7 :
                     listeBulunanlar.add("Altın Sandık keşfedildi ("+row--+","+col+")");
                     listeBulunanHazineler.add("Altın Sandık keşfedildi("+row--+","+col+")");
                     break;
                      case 8 :
                     listeBulunanlar.add("Gümüş Sandık  keşfedildi("+row--+","+col+")");
                     listeBulunanHazineler.add("Gümüş Sandık keşfedildi("+row--+","+col+")");
                     break;
                      case 9 :
                     listeBulunanlar.add("Bakır Sandık keşfedildi("+row--+","+col+")");
                     listeBulunanHazineler.add("Bakır Sandık keşfedildi("+row--+","+col+")");
                     break;
                      case 10 :
                     listeBulunanlar.add("Zümrüt Sandık  keşfedildi("+row--+","+col+")");
                     listeBulunanHazineler.add("Zümrüt Sandık keşfedildi("+row--+","+col+")");
                     break;
             }
         }
         }
         if(row+1<rowCount){
         openNode(node[row+1][col],kacinciHedef);
          if(node[row+1][col].solid==true || grid[row+1][col]==7|| grid[row+1][col]==8|| grid[row+1][col]==9|| grid[row+1][col]==10){
             int engelCesidi=grid[row+1][col];
             switch (engelCesidi) {
                 case 1 :
                   listeBulunanlar.add("Ağaç keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 2 :
                     listeBulunanlar.add("Kaya keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 3 :
                     listeBulunanlar.add("Duvar keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 4 :
                     listeBulunanlar.add("Dağ keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 5 :
                     listeBulunanlar.add("Kuş keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 6 :
                     listeBulunanlar.add("Arı keşfedildi("+(row+1)+","+col+")");
                     break;
                   case 7 :
                     listeBulunanlar.add("Altın Sandık keşfedildi ("+(row+1)+","+col+")");
                     listeBulunanHazineler.add("Altın Sandık keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 8 :
                     listeBulunanlar.add("Gümüş Sandık  keşfedildi("+(row+1)+","+col+")");
                     listeBulunanHazineler.add("Gümüş Sandık keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 9 :
                     listeBulunanlar.add("Bakır Sandık keşfedildi("+row+1+","+col+")");
                     listeBulunanHazineler.add("Bakır Sandık keşfedildi("+(row+1)+","+col+")");
                     break;
                      case 10 :
                     listeBulunanlar.add("Zümrüt Sandık  keşfedildi("+(row+1)+","+col+")");
                     listeBulunanHazineler.add("Zümrüt Sandık keşfedildi("+(row+1)+","+col+")");
                     break;
             }
         }
         }
         
         // en iyiyi node u buuyoruz
         int bestNodeIndex=0; //her seferinde yeni açık node lar arasındaa karşılaştır
         int bestNodefCost =999;
         for(int i=0;i<openList.size();i++){
             if(openList.get(i).fCost[kacinciHedef]<bestNodefCost){
                 bestNodeIndex=i;
                 bestNodefCost=openList.get(i).fCost[kacinciHedef];
             }
             else if(openList.get(i).fCost[kacinciHedef]==bestNodefCost){
                 if(openList.get(i).gCost[kacinciHedef]<openList.get(bestNodeIndex).gCost[kacinciHedef]){
                     bestNodeIndex=i;                   
                 }
                 
                 
             }
         }
         if(!openList.isEmpty()){
             
             currentNode=openList.get(bestNodeIndex);///null hatası alıyorum belki çözerrr
             System.out.println("open list dolu");
         //}
         
         if(currentNode==goalNode[kacinciHedef]){ ///hedefi bulduğum zaman yapacaklarım buradaaaaaaaaaaa
              Nodes.goal[kacinciHedef]=true;
             System.out.println("hedef bulundu ------------");
             goalReached[kacinciHedef]=true;
             if (kacinciHedef<kacTaneHedefVar) {///yeni güncelleme geldi
              //   kacinciHedef++;// önceki konnumm
                 if (kacinciHedef==kacTaneHedefVar-1) {
                      bulunanHedefler.add(goalNode[kacinciHedef]);
                     traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);
                  return -1;
                 }
                // traceBackThePath(goalNode[kacinciHedef],kacinciHedef);//aslında sadece else de var ididd
//                 
                 else{
                     bulunanHedefler.add(goalNode[kacinciHedef]);
                     traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);//aslında bu yoktu içimden geldi
                     kacinciHedef++;//bismillahirrahmanirrahim
                     //autoSearch(kacinciHedef);
                    // autoSearch(kacinciHedef);
//                     System.out.println("artttirilmissss Hedef : "+kacinciHedef);
//                     switch (kacinciHedef) {
//                         case 1:
//                             return 1;
//                         case 2:
//                             System.out.println("case de gerceklesti");
//                             return 2;
//                         case 3:
//                             return 3;
//                         default:
//                             throw new AssertionError();
//                     }
                // searchs(kacinciHedef);
                         }
             }
             else{
                  //searchs(kacinciHedef);
                 //traceBackThePath(goalNode[kacinciHedef],kacinciHedef);
             //traceBackThePath(goalNode[0],kacinciHedef);// bi kontrol etttttttttttt
           //  traceBackThePath();
             }
         }
          else{    
        for (int i = 0; i < kacTaneHedefVar; i++) {
                 if (bulunanHedefler.contains(currentNode)==false && currentNode==goalNode[i]) {
                      goalReached[i]=true;//parantezin içinde saçma bir şekilde kacinci hedef kalmişişş
                      bulunanHedefler.add(goalNode[i]);//16 mart 10.04//current vardi burda da
        //     if (kacinciHedef<kacTaneHedefVar) {///yeni güncelleme geldi                              
//   kacinciHedef++;//önceki yeri burasıydı 
//                 if (kacinciHedef==kacTaneHedefVar-1) {
//                     traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);
//                     
//                     //break;//16 mart 10.10
//                 }
         //    }
                // traceBackThePath(goalNode[kacinciHedef],kacinciHedef);//aslında sadece else de var ididd
                 
           //      else{
                     traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);
                    // autoSearch(kacinciHedef);
                    // autoSearch(kacinciHedef);//bismillahirrahmanirrahim
                      // traceBackTheAllPath(goalNode[kacinciHedef], kacinciHedef);//15 inde ekledim //16 mart10 10 cıkarttim
                     //kacinciHedef++;//bismillahirrahmanirrahim
                // autoSearch(kacinciHedef);
             //            }
          //   }
            // else{
            //     break;////eneenenenen son ekledim
            // }
                 }
             }
         }
         }//aslında bu yoktu içimden geldi
         else{
          //   if (kacinciHedef+1<kacTaneHedefVar) {
               System.out.println(kacinciHedef +". hedefi bulamadi.");
                 kacinciHedef++;//saaat 2100 16 amrt
             //closeNode(node[row][col], kacinciHedef);
               //  autoSearch(kacinciHedef);
            // }
           // bulamazsaSinirlandirmaKontrolü=10001; //saat 2105
           
           //   traceBackThePath(goalNode[0],kacinciHedef);/////buradaki else kısmı çok sonradan yazıldı siline de bilir 
            
         }
         bulamazsaSinirlandirmaKontrolü++;
     }
     else{
      //   if (kacinciHedef+1<kacTaneHedefVar) {
             kacinciHedef++;
             if (kacinciHedef==kacTaneHedefVar) {
             break;
         }
            // autoSearch(kacinciHedef);
        // }
        // else{
         //    break;
         //}
     }   
  }
 return 0;
   }
     
     

   @Override 
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
     
    private void drawGrid(Graphics g) {
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
             
                g.setColor(Color.BLACK);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                
                if(j>(columnCount/2)){// winter and summer situation
               if (sisEfekti[i][j]) {//bu ve 3 ü sis eklemek için eklenen satırlar
                       
                if (grid[i][j]==0) { // Boş hücreler
                    
                    g.setColor(Color.WHITE);
                }
                else if (grid[i][j]==-8) { // Engeller
                     g.setColor(Color.ORANGE);
                   // g.draw3DRect(j*32, i*32, 32, 32,false);
                   g.fill3DRect(j*32, i*32, 32, 32, true);
                }
                else if (grid[i][j]==-9) { // Engeller
                     g.setColor(Color.ORANGE);
                     g.fillRect(j*32, i*32, 32, 32);
                     g.setColor(Color.GREEN);
                     g.fillOval(j*32, i*32, 28, 28);
                     
                }
                
                else if (grid[i][j]==1) { // Engeller
                 
                    g.drawImage(Trees.tree, j*32, i*32,  null); // Arıyı çiz    
                  
                }
                else if(grid[i][j]==2){
                   
                    g.drawImage(Rocks.rock, j*32, i*32,  null);
                      
                }
                else if(grid[i][j]==3){
                   
                    g.drawImage(Walls.wall, j*32, i*32,null);
                    
                }
                else if(grid[i][j]==4){
                                      
                   g.drawImage(Mountains.mountain, j*32, i*32,  null);
                  
                }
                 else if(grid[i][j]==5){
                   
                    g.drawImage(Birds.bird,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==55){
                   
                    g.drawImage(Birds.upDown,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==6){
                                      
                    g.drawImage(Bees.arrow, j*32, i*32, null);
                     
                                   
                 }
                 else if(grid[i][j]==66){// burada j koordinatlarını bir dizide tutacağım 
                     g.drawImage(Bees.realBee, j*32, i*32, null);
                 }
                 else if(grid[i][j]==7){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.goldChest, j*32,i*32,  null);
                    
                 }
                 else if(grid[i][j]==8){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.silverChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==9){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.copperChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==10){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.emeraldChest, j*32, i*32,  null);
                    
                 }
                else if (grid[i][j]==-1) { // Engeller
                 //    g.setColor(Color.YELLOW);
                    g.drawImage(Minion.minion, j*32, i*32,  null); // Draw minion   
                   //   g.setColor(Color.YELLOW);
                }
                 g.drawImage(Fogs.fog, j*32, i*32,  null);//sona almadığım zaman üst üste biniyolar bir de böyle deniyim 
               }
               
               
        else
               {//son çarelerden biri
                     if (grid[i][j]==0) { // Boş hücreler
                    
                    g.setColor(Color.WHITE);
                } else if (grid[i][j]==1) { // Engeller
                 //    g.setColor(Color.YELLOW);
                    g.drawImage(Trees.tree, j*32, i*32,  null); // Arıyı çiz    
                   //   g.setColor(Color.YELLOW);
                }
                 else if (grid[i][j]==-8) { // Engeller
                     g.setColor(Color.ORANGE);
                 //   g.draw3DRect(j*32, i*32, 32, 32,false); 
                    g.fill3DRect(j*32, i*32, 32, 32, true);
                }
                  else if (grid[i][j]==-9) { // Engeller
                   g.setColor(Color.ORANGE);
                     g.fillRect(j*32, i*32, 32, 32);
                     g.setColor(Color.GREEN);
                     g.fillOval(j*32, i*32, 28, 28);
                }
                else if(grid[i][j]==2){
                   //  g.setColor(Color.YELLOW);
                    g.drawImage(Rocks.rock, j*32, i*32,  null);
                      
                }
                else if(grid[i][j]==3){
                    // g.setColor(Color.YELLOW);
                    g.drawImage(Walls.wall, j*32, i*32,null);
                    
                }
                else if(grid[i][j]==4){
                   // g.setPaintMode();
                    //g.setColor(Color.YELLOW);
                   g.drawImage(Mountains.mountain, j*32, i*32,  null);
                  
                }
                 else if(grid[i][j]==5){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.bird,j*32, i*32,  null);
                     
                 }
                  else if(grid[i][j]==55){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.upDown,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==6){
                    //  g.setColor(Color.RED);
                   
                    g.drawImage(Bees.arrow, j*32, i*32, null);
                     
                 //   g.drawImage(Bees.bee, j*24, i*24,  null);
                    
                 }
                 else if(grid[i][j]==66){// burada j koordinatlarını bir dizide tutacağım 
                     g.drawImage(Bees.realBee, j*32, i*32, null);
                 }
                 else if(grid[i][j]==7){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.goldChest, j*32,i*32,  null);
                    
                 }
                 else if(grid[i][j]==8){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.silverChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==9){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.copperChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==10){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.emeraldChest, j*32, i*32,  null);
                    
                 }
                    else if (grid[i][j]==-1) { // Engeller
                
                    g.drawImage(Minion.minion, j*32, i*32,  null); // Draw minion   
                   
                }  
               }
             }
                else{
                     if (sisEfekti[i][j]) {//bu ve 3 ü sis eklemek için eklenen satırlar
                       
                if (grid[i][j]==0) { // Boş hücreler
                    
                    g.setColor(Color.WHITE);
                }
                 else if (grid[i][j]==-8) { // Engeller
                     g.setColor(Color.ORANGE);
                   // g.draw3DRect(j*32, i*32, 32, 32,false);
                    g.fill3DRect(j*32, i*32, 32, 32, true);
                }
                  else if (grid[i][j]==-9) { // Engeller
                   g.setColor(Color.ORANGE);
                     g.fillRect(j*32, i*32, 32, 32);
                     g.setColor(Color.GREEN);
                     g.fillOval(j*32, i*32, 28, 28);
                }
                else if (grid[i][j]==1) { // Engeller
                 //    g.setColor(Color.YELLOW);
                    g.drawImage(Trees.winterTree, j*32, i*32,  null); // Arıyı çiz    
                   //   g.setColor(Color.YELLOW);
                }
                else if(grid[i][j]==2){
                   //  g.setColor(Color.YELLOW);
                    g.drawImage(Rocks.winterRock, j*32, i*32,  null);
                      
                }
                else if(grid[i][j]==3){
                    // g.setColor(Color.YELLOW);
                    g.drawImage(Walls.winterWall, j*32, i*32,null);
                    
                }
                else if(grid[i][j]==4){
                   // g.setPaintMode();
                    //g.setColor(Color.YELLOW);
                   g.drawImage(Mountains.winterMountain, j*32, i*32,  null);
                  
                }
                 else if(grid[i][j]==5){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.bird,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==55){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.upDown,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==6){
                    //  g.setColor(Color.RED);
                   
                    g.drawImage(Bees.arrow, j*32, i*32, null);
                     
                 //   g.drawImage(Bees.bee, j*24, i*24,  null);
                    
                 }
                 else if(grid[i][j]==66){// burada j koordinatlarını bir dizide tutacağım 
                     g.drawImage(Bees.realBee, j*32, i*32, null);
                 }
                 else if(grid[i][j]==7){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.goldChest, j*32,i*32,  null);
                    
                 }
                 else if(grid[i][j]==8){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.silverChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==9){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.copperChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==10){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.emeraldChest, j*32, i*32,  null);
                    
                 }
                else if (grid[i][j]==-1) { // Engeller
                 //    g.setColor(Color.YELLOW);
                    g.drawImage(Minion.minion, j*32, i*32,  null); // Draw minion   
                   //   g.setColor(Color.YELLOW);
                }
                 g.drawImage(Fogs.fog, j*32, i*32,  null);//sona almadığım zaman üst üste biniyolar bir de böyle deniyim 
               }
               
               
        else
               {//son çarelerden biri
                     if (grid[i][j]==0) { // Boş hücreler
                    
                    g.setColor(Color.WHITE);
                }
                  else if (grid[i][j]==-8) { // Engeller
                     g.setColor(Color.ORANGE);
                   // g.drawRect(j*32,i*32, 32, 32); // Arıyı çiz    
                    
                //  g.draw3DRect(j*32, i*32, 32, 32,false);
                  g.fill3DRect(j*32, i*32, 32, 32, true);
                } 
                   else if (grid[i][j]==-9) { // Engeller
                     g.setColor(Color.ORANGE);
                     g.fillRect(j*32, i*32, 32, 32);
                     g.setColor(Color.GREEN);
                     g.fillOval(j*32, i*32, 28, 28);
                }
                 else if (grid[i][j]==1) { // Engeller
                 //    g.setColor(Color.YELLOW);
                    g.drawImage(Trees.winterTree, j*32, i*32,  null); // Arıyı çiz    
                   //   g.setColor(Color.YELLOW);
                }
                else if(grid[i][j]==2){
                   //  g.setColor(Color.YELLOW);
                    g.drawImage(Rocks.winterRock, j*32, i*32,  null);
                      
                }
                else if(grid[i][j]==3){
                    // g.setColor(Color.YELLOW);
                    g.drawImage(Walls.winterWall, j*32, i*32,null);
                    
                }
                else if(grid[i][j]==4){
                   // g.setPaintMode();
                    //g.setColor(Color.YELLOW);
                   g.drawImage(Mountains.winterMountain, j*32, i*32,  null);
                  
                }
                 else if(grid[i][j]==5){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.bird,j*32, i*32,  null);
                     
                 }
                  else if(grid[i][j]==55){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Birds.upDown,j*32, i*32,  null);
                     
                 }
                 else if(grid[i][j]==6){
                    //  g.setColor(Color.RED);
                   
                    g.drawImage(Bees.arrow, j*32, i*32, null);
                     
                 //   g.drawImage(Bees.bee, j*24, i*24,  null);
                    
                 }
                 else if(grid[i][j]==66){// burada j koordinatlarını bir dizide tutacağım 
                     g.drawImage(Bees.realBee, j*32, i*32, null);
                 }
                 else if(grid[i][j]==7){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.goldChest, j*32,i*32,  null);
                    
                 }
                 else if(grid[i][j]==8){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.silverChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==9){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.copperChest, j*32, i*32,  null);
                    
                 }
                 else if(grid[i][j]==10){
                     // g.setColor(Color.YELLOW);
                    g.drawImage(Treasures.emeraldChest, j*32, i*32,  null);
                    
                 }
                    else if (grid[i][j]==-1) { // Engeller
                
                    g.drawImage(Minion.minion, j*32, i*32,  null); // Draw minion   
                   
                }  
               }
             }
               
 
            }
        }
    }

int ariKonumu;
   // Arıyı rastgele sağa veya sola hareket ettirme
   private void moveBee() {
    int direction = random.nextInt(2); // Rastgele yön seçme (0: sağ, 1: sol)

     ariKonumu=beeX;
        Random randomMoveDirection=new Random();
        Timer timer = new Timer(500, e -> {
           
            for (int i =0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    
                    //sisEfekti[i][j]=false;//en son dennmeme amaçlı yazdım
                    if(grid[i][j]==66){
                       
                        ariKonumu=j;
                      //  grid[i][j]=6;
                      
             int hareketYonuAri=randomMoveDirection.nextInt(2);
             if(grid[i][j+2]==6 &&(hareketYonuAri==1)&&(j<=ariKonumu+4)&&(j<columnCount-2)){//+2 ve -1 vardı 2 yle çarpıyorum
                 grid[i][j]=6;
                 grid[i+1][j]=6;
                  grid[i][j+1]=6;
                 grid[i+1][j+1]=6;
                //  gridPanel.repaint();
                 j++;
                 
                 grid[i][j]=66;
                 grid[i][j+1]=64;//bu 3 lüde 0 vardı 64 yapıyorum minion içinden geçemesin diye arının
                 grid[i+1][j]=64;
                 grid[i+1][j+1]=64;
                 gridPanel.repaint();
             }
             else if(grid[i][j-1]==6 && (hareketYonuAri==0) && (j>=ariKonumu-4) && (j>0)){// 2 yle çarptım yine
                 grid[i][j]=6;
                 grid[i+1][j]=6;
                 grid[i][j+1]=6;
                 grid[i+1][j+1]=6;
             
                 j --;
                 
                   grid[i][j]=66;
                   grid[i][j+1]=64;//burda da 0 vardı 64 yapıyorum 
                   grid[i+1][j]=64;
                   grid[i+1][j+1]=64;//tamamm artık geçemiyorrrr
                   gridPanel.repaint();
                   
             }
             else{
                   gridPanel.repaint();
             
             }
                    }
                }
            }
         //////////// Bird section
            for (int i =0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    
                    //sisEfekti[i][j]=false;//en son dennmeme amaçlı yazdım
                    if(grid[i][j]==5){
                       
                        birdLocation=i;// our technique always about bird location 
                      //  grid[i][j]=6;
                      
             int hareketYonuKus=randomMoveDirection.nextInt(2);
             if((i<rowCount-2) && grid[i+2][j]==55 &&(hareketYonuKus==1) &&(i<=birdLocation+4)){//+2 ve -1 vardı 2 yle çarpıyorum
                 grid[i][j]=55;
                 grid[i+1][j]=55;
                  grid[i][j+1]=55;
                 grid[i+1][j+1]=55;//6 yerine 55 
                //  gridPanel.repaint();
                 i++;
                 
                 grid[i][j]=5;
                 grid[i][j+1]=54;//bu 3 lüde 0 vardı 64 yapıyorum minion içinden geçemesin diye arının
                 grid[i+1][j]=54;
                 grid[i+1][j+1]=54;

                   gridPanel.repaint();
             }
             else if( (i>0) && grid[i-1][j]==55 && (hareketYonuKus==0) && (i>=birdLocation-4)){// 2 yle çarptım yine
                 grid[i][j]=55;
                 grid[i+1][j]=55;
                 grid[i][j+1]=55;
                 grid[i+1][j+1]=55;
          
                    i --;
                 
                   grid[i][j]=5;
                   grid[i][j+1]=54;//burda da 0 vardı 64 yapıyorum 
                   grid[i+1][j]=54;
                   grid[i+1][j+1]=54;//tamamm artık geçemiyorrrr
    
                   gridPanel.repaint();
                   
             }
             else{
                   gridPanel.repaint();
             
             }
                    }
                }
            }
             
        });
        timer.start();
       
    System.out.println(" repaint oncesi moveBee fonk ");
    //  gridPanel.repaint();
    System.out.println(" repaint sonrasi moveBee fonk ");
       
}
    
   
   
   
   
   
   
   
   
   public static void main(String[] args) {
        
    }//en iyisi
}

