package br.controle;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
 
public class ValidaLetras extends PlainDocument {
 
    private static final long serialVersionUID = 1L;
 
    public void insertString(int offs, String str,
            javax.swing.text.AttributeSet a) throws BadLocationException {

        for (int i = 0; i < str.length(); i++)
        	//VALIDA��O CRIADA NA FOR�A DA RAIVA #VAI ME PAGAR FELIPE.BARROS-MV
            //Caso o usu�rio digite algo diferente de n�mero, ponto ou v�rgula ser� apresentada a mensagem.
        	//ANOTADO FELIPE BARROS
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ','
                    && str.charAt(i) != '.') {   	

            	JOptionPane.showMessageDialog(null, "Aten��o: Favor informar apenas n�meros.", "Aten��o - Bat tools", JOptionPane.WARNING_MESSAGE);
            	return;
            }
 
        super.insertString(offs, str, a);
    	
    }
}
