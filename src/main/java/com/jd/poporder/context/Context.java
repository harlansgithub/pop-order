package com.jd.poporder.context;

import com.jd.poporder.core.Entry;
import com.jd.poporder.node.DefaultNode;
import com.jd.poporder.node.Node;

/**
 * 数据统计上下文
 */
public class Context {
    // 当前正在处理的校验的节点（校验链条）
    private Entry curEntry;
    
    // context name
    private String name;
    
    // 当前上下文的数据统计节点
    private DefaultNode entranceNode;

    // 超过最大上下文的情况时，不在创建新的上下文，返回一个NullContext用来标识此种情况
    public static final Context NULL_CONTEXT = new NullContext();

    // 是否异步处理
    public boolean async;

    public Context(DefaultNode defaultNode,String name) {
        this(defaultNode,name,false);
    }

    public Context(DefaultNode defaultNode, String name, boolean async) {
        this.entranceNode = defaultNode;
        this.name = name;
        this.async = async;
    }

    public DefaultNode getEntranceNode() {
        return entranceNode;
    }


    public String getName() {
        return name;
    }

    public Entry getCurEntry(){
        return this.curEntry;
    }

    public void setCurEntry(Entry entry){
        this.curEntry = entry;
    }

    public Context setCurNode(Node node){
        this.curEntry.setCurNode(node);
        return this;
    }
    public Node getCurNode(){
        return this.curEntry.getCurNode();
    }

}
