package code.projectifiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import code.controls.*;

/***
 * Class for saving project in file
 */
public class Saver {
    public static void save(Canvas canvas, File file) throws IOException
    {
        FileWriter writer = new FileWriter(file);
        writer.write( Integer.toString(canvas.listOfInputNodes.size() + canvas.listOfNodes.size() + canvas.listOfOutputNodes.size()) );
        writer.append("\n");
        for( InputNode in : canvas.listOfInputNodes ) writer.append(String.valueOf(in.id)).append(":").append(in.getClass().getSimpleName()).append("\n");
        for( AbstractNode node : canvas.listOfNodes ) writer.append(String.valueOf(node.id)).append(":").append(node.getClass().getSimpleName()).append("\n");
        for( OutputNode out : canvas.listOfOutputNodes ) writer.append(String.valueOf(out.id)).append(":").append(out.getClass().getSimpleName()).append("\n");
        writer.append("----\n");
        for( Link link : canvas.listOfLinks ) writer.append(String.valueOf(link.source.node.id))
                                                    .append(String.valueOf('>'))
                                                    .append(link.source.label.getText())
                                                    .append(String.valueOf(':'))
                                                    .append(String.valueOf(link.target.node.id))
                                                    .append(String.valueOf('>'))
                                                    .append(link.target.label.getText())
                                                    .append("\n");
        writer.close();
    }
}
