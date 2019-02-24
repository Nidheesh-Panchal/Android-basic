package com.example.myapplication;

public class qanda {
    private int qid;
    private boolean ans;
    public qanda(int qid,boolean ans)
    {
        this.qid=qid;
        this.ans=ans;
    }

    public int getQid()
    {
        return qid;
    }
    public boolean isAns()
    {
        return ans;
    }
}
