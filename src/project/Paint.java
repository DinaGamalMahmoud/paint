
package project;

import java.applet.Applet;
import java.awt.*;
import static java.awt.Color.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;


public class Paint extends Applet {

   class Shape
   {
   int x1;
   int xedit;
   int yedit;
   int y1;
   int x2;
   int y2;
   Color mycolor;
   char fill;
   public void draw(Graphics g)
   {
   }
   
   public Color mycolor()
   {
    if(c.getSelectedIndex()==0)
        selected=black;
     if(c.getSelectedIndex()==1)
        selected=red;
     if(c.getSelectedIndex()==2)
        selected=blue;
     if(c.getSelectedIndex()==3)
        selected=green;
    return selected;
  }

    public char fill()
    {
    if(f.getState()==false)
       fnf='f';
    else 
       fnf='t'; 
    return fnf;
    }
   }
   
   class Rect extends Shape
   {
    public void draw(Graphics g)
   {
       g.setColor(mycolor);
       if(fill=='t')
           g.fillRect(x1, y1, x2, y2);
       else
          g.drawRect(x1, y1, x2, y2); 
   }
   }
   class Line extends Shape
   {
    public void draw(Graphics g)
   {
        g.setColor(mycolor);
          g.drawLine(x1, y1, x2, y2); 
   }
   }
   class Oval extends Shape
   {
    public void draw(Graphics g)
   {
        g.setColor(mycolor);
       if(fill=='t')
           g.fillOval(x1, y1, x2, y2);
       else
          g.drawOval(x1, y1, x2, y2); 
   }
   }
   
   class Delete extends Shape
   {  
    public void draw(Graphics g)
   {
       
        g.setColor(white); 
         g.fillRect(x1, y1, x2, y2);
       
   }
   }
   
   
   
   ArrayList<Shape> myshape=new ArrayList();

   Shape sh;
   Color selected;
   char fnf;
   
   //gui intialitization
    Choice c =new Choice();
    Button rect=new Button("Rectangle");
    Button oval=new Button("Oval");
    Button line=new Button("Line");
    Button erase=new Button("Earse");
    Button deleteall=new Button("Earse all");
    Checkbox f= new Checkbox("Fill");   
    Button save=new Button("Save");
    Button undo=new Button("Undo");


   
   
   char flag; 
   
   
    
    @Override
    public void init()
    {
        
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
 
                FileWriter fileWriter = null; 
                        PrintWriter printWriter = null; 
                        BufferedReader bufferedReader=null; 
                        try{ 
                        fileWriter = new FileWriter("sample.txt", true); 
                        printWriter = new PrintWriter(fileWriter); 
                        printWriter.println(); 
                        for(Shape data : myshape){ printWriter.println(data); } 
                        }
            catch (IOException el)
            { el.printStackTrace(); } 
                        finally{ 
                    try{ printWriter.close();
                         fileWriter.close(); }
catch (IOException ef) { ef.printStackTrace(); }
                        
                        }
            }
                });
        add(save);
        
               
        deleteall.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
           flag='d';
           sh=new Delete();
               sh.x1=0;
               sh.y1=0;
               sh.x2=getWidth();
               sh.y2=getHeight();
               sh.mycolor=white;
               sh.fill='t';
                myshape.add(sh);
           repaint();
            }
        });
        
        undo.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(myshape.size()>0)
              
                {flag='u';
         myshape.remove(myshape.size()-1);
           repaint();
                }
            }
        });
        add(undo);
    
     erase.addActionListener(new ActionListener(){
         @Override
         public void actionPerformed(ActionEvent e) {
          flag='e';
         }
     });
     
        add(erase);
        add(deleteall);
        c.add("black");
        c.add("red");
        c.add("blue");
        c.add("green");
        add(c);
       
        rect.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
         flag='r';
            }
        });
        add(rect);
        
        oval.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                 flag='o';
            }
        });
        add(oval);
        
        line.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                 flag='l';
            }
        });
        add(line);
       
      
        add(f); 
        this.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) 
            {
               if( flag=='r')
               {
               sh=new Rect();
               sh.x1=e.getX();
               sh.y1=e.getY();
               sh.mycolor=sh.mycolor();
               sh.fill=sh.fill();
                myshape.add(sh);
               }
                if( flag=='o')
               {
               sh=new Oval();
               sh.x1=e.getX();
               sh.y1=e.getY();
               sh.mycolor=sh.mycolor();
               sh.fill=sh.fill();
               myshape.add(sh);
               }
                 if( flag=='l')
               {
               sh=new Line();
               sh.x1=e.getX();
               sh.y1=e.getY();
               sh.mycolor=sh.mycolor();
               sh.fill='f';
               myshape.add(sh);
               }
                 if(flag=='e')
                     {
           sh=new Delete();
          sh.fill='f';
          sh.mycolor=white;
           sh.x1=e.getX();
           sh.y1=e.getY();
           myshape.add(sh);
                     }
                 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
         
            }

            @Override
            public void mouseEntered(MouseEvent e) {
               
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        this.addMouseMotionListener(new MouseMotionListener(){
            @Override
            public void mouseDragged(MouseEvent e) {
              
             if( flag=='r')
               {
               sh.x2=e.getX()-sh.x1;
               sh.y2=e.getY()-sh.y1;
             
               }
                if( flag=='o')
               {
               sh.x2=e.getX()-sh.x1;
               sh.y2=e.getY()-sh.y1;
               
               }
                 if( flag=='l')
               {
               sh.x2=e.getX();
               sh.y2=e.getY();
           
               }
                 if(flag=='e')
                 {
               sh.x2=e.getX()-sh.x1;
               sh.y2=e.getY()-sh.y1;
                 }
                
                    repaint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                
            }
        });
    }
        
   @Override
   public void paint(Graphics g)
{
for(int i=0;i<myshape.size();i++)
{
    myshape.get(i).draw(g);
}
}   
}
