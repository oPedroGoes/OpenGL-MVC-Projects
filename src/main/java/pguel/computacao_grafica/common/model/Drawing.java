package pguel.computacao_grafica.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Modelo para se adicionar figuras em uma lista List<Figures>
public class Drawing {
    private List<Figure> drawing;

    public Drawing (){
        drawing = new ArrayList<>();
    }

    public List<Figure> getFigures(){
        return Collections.unmodifiableList(drawing);
    }

    public boolean addFigure(Figure newFigure){
        return drawing.add(newFigure);
    }

    public boolean popFigure(Figure oldFigure) {
        return drawing.remove(oldFigure);
    }

    public Figure popFigure(int index){
        return drawing.remove(index);
    }
}

