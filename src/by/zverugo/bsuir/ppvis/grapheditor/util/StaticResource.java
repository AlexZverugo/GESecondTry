package by.zverugo.bsuir.ppvis.grapheditor.util;

/**
 * Created by Alex on 15.03.2015.
 */
public enum StaticResource {
    OPEN_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/open.png"),
    COPY_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/copy.png"),
    SCISSORS_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/scissors.png"),
    PASTE_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/paste.png"),
    VERTEX_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/vertex.png"),
    ARC_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/arc.png"),
    DELETE_VERTEX_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/deletevertex.png"),
    MOVE_VERTEX_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/dragger.png"),
    GRAPH_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/color.png"),
    SAVE_IMAGE("src/by/zverugo/bsuir/ppvis/grapheditor/resources/save.png");

    private String path;

    StaticResource(String path) {
        this.path = path;
    }

    public String path() {
        return path;
    }
}
