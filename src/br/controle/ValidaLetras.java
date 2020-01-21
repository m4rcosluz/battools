package br.controle;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
 
public class ValidaLetras extends PlainDocument {
 
    private static final long serialVersionUID = 1L;
 
    public void insertString(int offs, String str,
            javax.swing.text.AttributeSet a) throws BadLocationException {

        for (int i = 0; i < str.length(); i++)
        	//VALIDAÇÃO CRIADA NA FORÇA DA RAIVA #VAI ME PAGAR FELIPE.BARROS-MV
            //Caso o usuário digite algo diferente de número, ponto ou vírgula será apresentada a mensagem.
        	//ANOTADO FELIPE BARROS
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ','
                    && str.charAt(i) != '.') {   	

            	JOptionPane.showMessageDialog(null, "Atenção: Favor informar apenas números.", "Atenção - Bat tools", JOptionPane.WARNING_MESSAGE);
            	return;
            }
 
        super.insertString(offs, str, a);
    	
    }
}
