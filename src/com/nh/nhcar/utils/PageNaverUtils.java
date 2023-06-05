package com.nh.nhcar.utils;

import javax.servlet.http.HttpServletRequest;

public class PageNaverUtils {
	private long Start=0;//��ʼ��
	private int ListPage=2;//ÿҳ��ʾ��
	private String Parameter="pno";//ҳ����תʱҪ���Ĳ���
	private long Count;//����
	private int CurrentPage=1;//��ǰҳ
	private int TotalPages;//��ҳ��
	private int ListNumber=2;//Const.LISTNUMBER;ǰ����ʾ����
	private String Url;
	private HttpServletRequest curRequest;
	public PageNaverUtils(HttpServletRequest request){
		curRequest=request;
	}
	public long getStart() {
		return Start;
	}
	public void setListNumber(int listNumber){
		this.ListNumber=listNumber;
	}
	public void setListPage(int listPage){
		this.ListPage=listPage;
	}
	public int getListPage() {
		return ListPage;
	}
	private void CalculatePage(int currentPage)
	{
		if(currentPage<=1)
			this.CurrentPage=1;
		else
			this.CurrentPage=currentPage;
		this.Start=(this.CurrentPage-1)*this.ListPage;
	}
	private void SetTotalPages()
	{
		if(this.Count%this.ListPage==0)
			this.TotalPages=(int) (this.Count/this.ListPage);
		else
			this.TotalPages=(int) (this.Count/this.ListPage+1);
	}
	//������������ͬ��
	private void GetParameter()
	{
		Url=curRequest.getQueryString();
		//���û�в���
		if(Url==null){
			Url=curRequest.getRequestURI()+"?";//��ȡ��ǰҳ���ַ
		}
		else{
			//�������(��ֻp,������������)
			if(Url.indexOf(this.Parameter+"=")>=0){
				if(Url.indexOf("&"+this.Parameter+"=")>=0){
				Url=Url.substring(0,Url.indexOf("&"+this.Parameter+"="));
				Url=curRequest.getRequestURI()+"?"+Url+"&";
				}
				else{
					Url=curRequest.getRequestURI()+"?";
				}
			}
			else{
				Url=curRequest.getRequestURI()+"?"+Url+"&";//��ȡ��ǰҳ���ַ
			}
		}
	}
	//��ʼ��ҳ
	public String ShowPager(long count)
	{
		this.GetParameter();
		this.Count=count;
		if(curRequest.getParameter(this.Parameter)!=null){
			this.CalculatePage(Integer.parseInt(curRequest.getParameter(this.Parameter)));
		}else{
			this.CalculatePage(1);
		}
		this.SetTotalPages();
		String Page="";
		//Page=Page+this.CurrentPage+"/"+this.TotalPages+" ҳ";
		String a_class="class='number'";
		String current="class='number current'";
		if(this.CurrentPage-this.ListNumber>0){
			Page=Page+"  <a href='"+Url+this.Parameter+"=1' "+current+">��һҳ</a>";
			Page=Page+"  <a href='"+Url+this.Parameter+"="+(int)(this.CurrentPage-this.ListNumber)+"' "+current+">ǰ"+this.ListNumber+"ҳ</a>";
		}
		if(this.CurrentPage>1)
			Page=Page+"  <a href='"+Url+this.Parameter+"="+(int)(this.CurrentPage-1)+"' "+current+">&lt;</a>";
		if(this.CurrentPage<this.TotalPages)
			Page=Page+"  <a href='"+Url+this.Parameter+"="+(int)(this.CurrentPage+1)+"' "+current+">&gt;</a>";
		if(this.CurrentPage>1){
			String PageVo="";
			for(int i=this.CurrentPage-1;i>this.CurrentPage-this.ListNumber;i--){
				if(i<1)
					break;
				PageVo="<a href='"+Url+this.Parameter+"="+i+"' "+a_class+">"+i+"</a>"+PageVo;
			}
			Page=Page+PageVo;
		}
		if(this.CurrentPage<=this.TotalPages && this.TotalPages!=1){
			for(int i=this.CurrentPage;i<this.CurrentPage+this.ListNumber;i++){
				if(i==this.CurrentPage){
					Page=Page+"<span>"+i+"</span>";
					continue;
				}
				if(i>this.TotalPages)
					break;
				Page=Page+"<a href='"+Url+this.Parameter+"="+i+"' "+a_class+">"+i+"</a>";
			}
		}
		if(this.CurrentPage+this.ListNumber<this.TotalPages)
		{
			Page=Page+"  <a href='"+Url+this.Parameter+"="+(int)(this.CurrentPage+this.ListNumber)+"' "+current+">��"+this.ListNumber+"ҳ</a>";
			Page=Page+"  <a href='"+Url+this.Parameter+"="+this.TotalPages+"' "+current+">���һҳ</a>";
		}
		return Page;
	}
}

