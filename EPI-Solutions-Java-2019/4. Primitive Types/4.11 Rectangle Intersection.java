/*

Important questions
what cases does intersection between two rectangles include


Observation
There might be many cases where two rectangles intersect:
1. share common overlap area 
2. share same boundary
3. share one common vertex
4. one rectangle is within another rectangle
5. one rectangle passes through another 


Note: Before attempting the question, try to clarify conditions included to conclude intersection between two rectangle 


There are many cases and considering building logic around every case might get difficult
Instead we try to look for cases where two rectangles do not intesect and formulate a solution

For given two rectangles with left lowermost points, width and height as follows:
1. (1,2), 3, 4
2. (5,3), 2, 4

Notice how
1. x values including the width dont intersect
2. y values including the height through intersect, they dont overlap due to range of values in x 

If x and y values overlap, there must be a common area/point common to both rectangles

Implementation
For two rectangles r1 & r2 to intersection, both range of x and y values should overlap
Therefore, all four conditions must satisfy
1. r1.x + r1.width >= r2.x  
2. r2.x + r2.width >= r1.x
3. r1.y + r1.height >= r2.height
4. r2.y + r2.height >= r1.height


For any variant of the problem, notice how we only need the following to arrive at an answer:
1. lowest (x,y) points of two rectangles
2. width of both rectangles
3. height of both rectangles


*/


public static class Rectangle{
    int x, y, width, height;

    Rectangle(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

//Returns common intersection area
public static Rectangle intersect(Rectangle r1, Rectangle r2){

    if(!isIntersect(r1,r2)){
        return new Rectangle(0,0,-1,-1);
    }

    
    return new Rectangle(
        Math.max(r1.x,r2.x), Math.max(r1.y, r2.y),
        Math.min(r1.x + r1.width, r2.x + r2.width) - Math.max(r1.x,r2.x),
        Math.min(r1.y + r1.height, r2.y + r2.height) - Math.max(r1.y, r2.y)
    )
}

public static boolean isIntersect(Rectangle r1, Rectangle r2){
    return r1.x + r1.width >= r2.x  
            && r2.x + r2.width >= r1.x
            && r1.y + r1.height >= r2.height
            && r2.y + r2.height >= r1.height;
}