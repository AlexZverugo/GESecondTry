package by.zverugo.bsuir.ppvis.grapheditor.main;

import by.zverugo.bsuir.ppvis.grapheditor.view.MainFrame;

import javax.swing.SwingUtilities;


public class StartProgram {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run (){
                new MainFrame();
            }
        });
    }

}
