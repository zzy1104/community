package com.newcoder.community.entity;

/**
 * 封装分页相关信息
 *
 */
public class Page {
    // 当前页码
    private int current=1;
    //显示上限
    private int limit=10;
    //数据总数  可以计算出总的页数
    private int rows;
    //查询路径（复用分页连接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
        this.current = current;}
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if(limit>=1 && limit<=100){
            this.limit = limit;
        }

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows>=0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行，通过当前页来算
     * @return
     */
    public int getOffset(){
        //current * limit -limit 就是这一页的起始行
        return (current-1)*limit;
    }
    /**
     * 获取总的页数
     * @return
     */
    public int getTotal(){
        //rows / limit  [+1] 要判断能不能整除
        if(rows % limit ==0){
            return rows/limit;
        }
        else{
            return rows/limit-1;
        }
    }
    /**
     * 获取起始页码  例如当前第8页，前面显示到6
     * @return
     */
    public int getFrom(){
        int from=current-2;
        //如果当前是第1页第2页，减去2以后就是-1,0。起始页就设置为1，否则就是计算出的真实数
        return from<1 ? 1 :from;
    }
    public int getTo(){
        int to=current+2;
        int total= getTotal();
        return to>total ? total :to;

    }
}
