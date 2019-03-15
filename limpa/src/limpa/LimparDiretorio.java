package limpa;

import java.io.File;

import org.omg.CORBA.ARG_IN;

class LimparDiretorio {
	
    public void remover (File f) {
        
    	if (f.isDirectory()) { 	
        	
            //lista de todos os arquivos dentro do diretorio f
        	File[] lista = f.listFiles();
            //armazena sempre o arquivo mais recente dentro do diretorio f
            File maisRecente = null;
        	
            //percorre todos os arquivos dentro de f
           	for(int i=0; i < lista.length; i++){
                //se o arquivo dentro de f é uma pasta, chama o remover de novo            	
            	if (lista[i].isDirectory()) {            		
            		remover(lista[i]);
            	}
                //se não, trata todos os arquivos, deixando apenas o mais recente
            	else 
            	{
                    //se maisRecente é null ele é o primeiro arquivo a ser selecionado
            		if (maisRecente == null) {
                        maisRecente = lista[i];
                    } else {
                        //se maisRecente não é null, é porque tem mais de um arquivo dentro desta pasta
                        //assim, com base na ultima modificacao, mantem no maisRecente somente o mais recente
                        // e remove os outros
                        if (lista[i].lastModified() > maisRecente.lastModified()) {
                        	System.out.println(lista[i].lastModified());
                            maisRecente.delete();
                            maisRecente = lista[i];
                        } else {
                            lista[i].delete();
                        }
                    }
                } 
           	}
               
    	}  
        	    		
    }
        	
    
    public static void main(String[] args) {
        LimparDiretorio ld = new LimparDiretorio();
        ld.remover (new File ("c:\\BACKUP CLIENTES\\"));
    }
}