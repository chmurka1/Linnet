package code.projectifiles;

import code.controls.AbstractNode;
import code.controls.Canvas;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Loader {
    public static void load(Canvas canvas, File file) throws Exception {
        canvas.clear();
        Scanner reader = new Scanner(file);
        String data = reader.nextLine();
        int n = Integer.parseInt(data);
        HashMap<Integer,AbstractNode> nodeHashMap = new HashMap<>();
        for( int i = 0; i < n; i++ ) {
            data = reader.nextLine();
            String[] arr = data.split(":");
            String[] coords = arr[2].split(",");
            System.out.println(Arrays.deepToString(arr));
            switch (arr[1]) {
                case "InputNode" -> {
                    canvas.addInputNode();
                    canvas.listOfInputNodes.get(canvas.listOfInputNodes.size() - 1).id = Integer.parseInt(arr[0]);
                    canvas.listOfInputNodes.get(canvas.listOfInputNodes.size() - 1)
                            .setPosition(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
                    nodeHashMap.put(Integer.parseInt(arr[0]), canvas.listOfInputNodes.get(canvas.listOfInputNodes.size() - 1));
                }
                case "OutputNode" -> {
                    canvas.addOutputNode();
                    canvas.listOfOutputNodes.get(canvas.listOfOutputNodes.size() - 1).id = Integer.parseInt(arr[0]);
                    canvas.listOfOutputNodes.get(canvas.listOfOutputNodes.size() - 1)
                            .setPosition(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
                    nodeHashMap.put(Integer.parseInt(arr[0]), canvas.listOfOutputNodes.get(canvas.listOfOutputNodes.size() - 1));
                }
                default -> {
                    AbstractNode node = (AbstractNode) (Class.forName("code.controls." + arr[1]).getConstructor(Canvas.class).newInstance(canvas));
                    canvas.addNode(node);
                    node.id = Integer.parseInt(arr[0]);
                    node.setPosition(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
                    nodeHashMap.put(Integer.parseInt(arr[0]), canvas.listOfNodes.get(canvas.listOfNodes.size() - 1));
                }
            }
        }
        data = reader.nextLine();
        while (reader.hasNextLine()) {
            data = reader.nextLine();
            String[] arr = data.split(":");
            String[] sourceDesc = arr[0].split(">");
            String[] targetDesc = arr[1].split(">");
            AbstractNode sourceNode = nodeHashMap.get(Integer.parseInt(sourceDesc[0]));
            AbstractNode targetNode = nodeHashMap.get(Integer.parseInt(targetDesc[0]));
            canvas.addLink(sourceNode.getSocketByName(sourceDesc[1]),targetNode.getSocketByName(targetDesc[1]));
        }
        reader.close();
    }
}
