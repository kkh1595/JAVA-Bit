package scoreProject;

import java.io.EOFException;
//import java.awt.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

class ScoreImpl implements Score {
    private List<ScoreDTO> list = new ArrayList<ScoreDTO>();
    private File file=null;

    public void input(ScoreDTO dto) {
        list.add(dto);

    }

    public void output(DefaultTableModel model) {
        model.setNumRows(0);
        for (ScoreDTO dto : list) {
            Vector<String> v = new Vector<String>();
            v.add(dto.getId() + "");
            v.add(dto.getName());
            v.add(dto.getKor() + "");
            v.add(dto.getEng() + "");
            v.add(dto.getMath() + "");
            v.add(dto.getTot() + "");
            v.add(dto.getAvg() + "");
            model.addRow(v);
        }
    }

    public int search(DefaultTableModel model, String id) {
        model.setNumRows(0);
        int sw = 0;
        for (ScoreDTO dto : list) {
            if (id.equals(dto.getId() + "")) {
                Vector<String> v = new Vector<String>();
                v.add(dto.getId() + "");
                v.add(dto.getName());
                v.add(dto.getKor() + "");
                v.add(dto.getEng() + "");
                v.add(dto.getMath() + "");
                v.add(dto.getTot() + "");
                v.add(dto.getAvg() + "");
                model.addRow(v);
                sw = 1;
            } // if
        } // for
        return sw;
    }

    public void to_desc() {
        Collections.sort(list);
    }

    public void load() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(null);        
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                list.clear();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);                
                while(true) {
                    try {
                        ScoreDTO dto = (ScoreDTO)ois.readObject();
                        list.add(dto);
                    }catch(EOFException e) {
                        break;
                    }
                }               
                //ois.close();
            }catch (IOException e) {
                e.printStackTrace();
            }catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            
        }
    }
    public void save() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            file = chooser.getSelectedFile();
            try {
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (file == null)
            return;
        try {
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (ScoreDTO dto : list) {
                oos.writeObject(dto);
            }
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
