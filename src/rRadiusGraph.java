
class rRadiusGraph
{
	static int num = 0;				//r�뾶��ͼ������
	double score;					//��ͼ�ĵ÷�
	int r;							//��ͼ�İ뾶
	int id;							//��ͼ�ı��		
	int M[][];						//��ͼ���ڽӾ���
	int keyVector[];				//����ͼ�����ؼ��ʵ�����

	public rRadiusGraph(int R, int Id, int m[][])
	{
		this.M = new int[matrix.mSize][matrix.mSize];
		rRadiusGraph.num++;
		this.r = R;
		this.id = Id;
		for (int i = 0; i <= matrix.mSize - 1; i++)
			for (int j = 0; j <= matrix.mSize - 1; j++)
				this.M[i][j] = m[i][j];

		
	}
	
	
	
}
