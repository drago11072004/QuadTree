public class PointQuadtree {

    enum Quad {
        NW,
        NE,
        SW,
        SE
    }

    public PointQuadtreeNode root;

    public PointQuadtree() {
        this.root = null;
    }

    public boolean insert(CellTower a) {
        // TO be completed by students
        // System.out.println("insert");
        PointQuadtreeNode new_node = new PointQuadtreeNode(a);
        if(root==null){root=new_node;return true;}
        PointQuadtreeNode ptr = root;
        while(true){
            if(ptr.celltower.x==a.x && ptr.celltower.y==a.y){return false;}
            if(ptr.celltower.x>a.x && ptr.celltower.y <=a.y){if(ptr.quadrants[0]==null){break;};ptr=ptr.quadrants[0];}
            else if(ptr.celltower.x<=a.x && ptr.celltower.y <a.y){if(ptr.quadrants[1]==null){break;};ptr=ptr.quadrants[1];}
            else if(ptr.celltower.x>=a.x && ptr.celltower.y >a.y){if(ptr.quadrants[2]==null){break;};ptr=ptr.quadrants[2];}
            else if(ptr.celltower.x<a.x && ptr.celltower.y >=a.y){if(ptr.quadrants[3]==null){break;};ptr=ptr.quadrants[3];}
        }
        if(ptr.celltower.x>a.x && ptr.celltower.y <=a.y){ptr.quadrants[0]=new_node;}
        else if(ptr.celltower.x<=a.x && ptr.celltower.y <a.y){ptr.quadrants[1]=new_node;}
        else if(ptr.celltower.x>=a.x && ptr.celltower.y >a.y){ptr.quadrants[2]=new_node;}
        else if(ptr.celltower.x<a.x && ptr.celltower.y >=a.y){ptr.quadrants[3]=new_node;}
        return true;
    }

    public boolean cellTowerAt(int x, int y) {
        // TO be completed by students
        // System.out.println("cellTowerAt");
        PointQuadtreeNode ptr = root;
        while(ptr!=null){
            if(ptr.celltower.x==x && ptr.celltower.y==y){return true;}
            if(ptr.celltower.x>x && ptr.celltower.y <=y){ptr=ptr.quadrants[0];}
            else if(ptr.celltower.x<=x && ptr.celltower.y <y){ptr=ptr.quadrants[1];}
            else if(ptr.celltower.x>=x && ptr.celltower.y >y){ptr=ptr.quadrants[2];}
            else if(ptr.celltower.x<x && ptr.celltower.y >=y){ptr=ptr.quadrants[3];}
        }
        return false;
    }

    // public CellTower chooseCellTower(PointQuadtreeNode ptr,int x, int y, int r, CellTower ans){
    //     if(ptr==null){return ans;}
    //     if(ans==null && ptr.celltower.distance(x, y)<=r){ans=ptr.celltower;}
    //     if(ans!=null){if(ans.cost!=-1 && ans.cost>ptr.celltower.cost && ptr.celltower.distance(x, y)<=r){ans=ptr.celltower;}}
    //     if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)>r){
    //         if(ptr.celltower.x>x && ptr.celltower.y <=y){ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);}
    //         else if(ptr.celltower.x<=x && ptr.celltower.y <y){ans= chooseCellTower(ptr.quadrants[1], x, y, r, ans);}
    //         else if(ptr.celltower.x>=x && ptr.celltower.y >y){ans= chooseCellTower(ptr.quadrants[2], x, y, r, ans);}
    //         else if(ptr.celltower.x<x && ptr.celltower.y >=y){ans= chooseCellTower(ptr.quadrants[3], x, y, r, ans);}
    //     }
    //     else{
    //         ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
    //         ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
    //         ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
    //         ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
    //     }
    //     return ans;
    // }

    public CellTower chooseCellTower(PointQuadtreeNode ptr,int x, int y, int r, CellTower ans){
        if(ptr==null){return ans;}
        if(ans==null && ptr.celltower.distance(x, y)<=r){ans=ptr.celltower;}
        if(ans!=null){if(ans.cost!=-1 && ans.cost>ptr.celltower.cost && ptr.celltower.distance(x, y)<=r){ans=ptr.celltower;}}
        int  q =0;

        if(ptr.celltower.x>x && ptr.celltower.y <=y){q=0;}//ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);}
        else if(ptr.celltower.x<=x && ptr.celltower.y <y){q=1;}//ans= chooseCellTower(ptr.quadrants[1], x, y, r, ans);}
        else if(ptr.celltower.x>=x && ptr.celltower.y >y){q=2;}//ans= chooseCellTower(ptr.quadrants[2], x, y, r, ans);}
        else if(ptr.celltower.x<x && ptr.celltower.y >=y){q=3;}//ans= chooseCellTower(ptr.quadrants[3], x, y, r, ans);}
       
        if(q==0){
            if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)<=r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);;ans=chooseCellTower(ptr.quadrants[q+2], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)<=r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q+1], x, y, r, ans);}
            else{
                ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
            }
        }
        else if(q==1){
            if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)<=r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q+2], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)<=r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q-1], x, y, r, ans);}
            else{
                ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
            }
        }
        else if(q==2){
            if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)<=r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q-2], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)<=r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q+1], x, y, r, ans);}
            else{
                ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
            }
        }
        else if(q==3){
            if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)>r && Math.abs(ptr.celltower.y-y)<=r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q-2], x, y, r, ans);}
            else if(Math.abs(ptr.celltower.x-x)<=r && Math.abs(ptr.celltower.y-y)>r){ans=chooseCellTower(ptr.quadrants[q], x, y, r, ans);ans=chooseCellTower(ptr.quadrants[q-1], x, y, r, ans);}
            else{
                ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
                ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
            }
        }

        // else{
        //     ans=chooseCellTower(ptr.quadrants[0], x, y, r, ans);
        //     ans=chooseCellTower(ptr.quadrants[1], x, y, r, ans);
        //     ans=chooseCellTower(ptr.quadrants[2], x, y, r, ans);
        //     ans=chooseCellTower(ptr.quadrants[3], x, y, r, ans);
        // }
        return ans;
    }



    public CellTower chooseCellTower(int x, int y, int r) {
        // TO be completed by students
        CellTower ans = null;
        ans=chooseCellTower(root, x, y, r, ans);
        return ans;
    }

    
    
}
