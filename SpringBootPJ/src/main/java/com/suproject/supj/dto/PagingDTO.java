package com.suproject.supj.dto;

public class PagingDTO {
	// ����������, ����������, ��������, �Խñ� �� ����, �������� �� ����, ������������, SQL������ �� start, end
		private int nowPage, startPage, endPage, total, cntPerPage, lastPage, start, end;
		private int cntPage = 5;
		private String kind, need;
		
		
		public PagingDTO() {
			startPage=1;
		}
		public PagingDTO(int total, int nowPage, int cntPerPage,String kind, String need) {
			SetNowPage(nowPage);
			SetCntPerPage(cntPerPage);
			SetTotal(total);
			CalcLastPage(GetTotal(), GetCntPerPage());
			CalcStartEndPage(GetNowPage(), cntPage);
			CalcStartEnd(GetNowPage(), GetCntPerPage());
			setKind(kind);
			setNeed(need);
		}
		// ���� ������ ������ ���
		public void CalcLastPage(int total, int cntPerPage) {
			SetLastPage((int) Math.ceil((double)total / (double)cntPerPage));
		}
		// ����, �� ������ ���
		public void CalcStartEndPage(int nowPage, int cntPage) {
			SetEndPage(((int)Math.ceil((double)nowPage / (double)cntPage)) * cntPage);
			if (GetLastPage() < GetEndPage()) {
				SetEndPage(GetLastPage());
			}
			SetStartPage(GetEndPage() - cntPage + 1);
			if (GetStartPage() < 1) {
				SetStartPage(1);
			}
		}
		// DB �������� ����� start, end�� ���
		public void CalcStartEnd(int nowPage, int cntPerPage) {
			SetEnd(nowPage * cntPerPage);
			SetStart(GetEnd() - cntPerPage + 1);
		}
		
		public int GetNowPage() {
			return nowPage;
		}
		public void SetNowPage(int nowPage) {
			this.nowPage = nowPage;
		}
		public int GetStartPage() {
			return startPage;
		}
		public void SetStartPage(int startPage) {
			this.startPage = startPage;
		}
		public int GetEndPage() {
			return endPage;
		}
		public void SetEndPage(int endPage) {
			this.endPage = endPage;
		}
		public int GetTotal() {
			return total;
		}
		public void SetTotal(int total) {
			this.total = total;
		}
		public int GetCntPerPage() {
			return cntPerPage;
		}
		public void SetCntPerPage(int cntPerPage) {
			this.cntPerPage = cntPerPage;
		}
		public int GetLastPage() {
			return lastPage;
		}
		public void SetLastPage(int lastPage) {
			this.lastPage = lastPage;
		}
		public int GetStart() {
			return start;
		}
		public void SetStart(int start) {
			this.start = start;
		}
		public int GetEnd() {
			return end;
		}
		public void SetEnd(int end) {
			this.end = end;
		}	
		public int SetCntPage() {
			return cntPage;
		}
		public void GetCntPage(int cntPage) {
			this.cntPage = cntPage;
		}
		@Override
		public String toString() {
			return "PagingDTO [nowPage=" + nowPage + ", startPage=" + startPage + ", endPage=" + endPage + ", total=" + total
					+ ", cntPerPage=" + cntPerPage + ", lastPage=" + lastPage + ", start=" + start + ", end=" + end
					+ ", cntPage=" + cntPage + "]";
		}
		public String getKind() {
			return kind;
		}
		public void setKind(String kind) {
			this.kind = kind;
		}
		public String getNeed() {
			return need;
		}
		public void setNeed(String need) {
			this.need = need;
		}



}
