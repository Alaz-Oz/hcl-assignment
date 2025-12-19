package hcl_training.assignment_1;

import java.util.Scanner;

public class Solution3 {
	

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Matrix A");
		Matrix matA = getMatrix(sc);
		System.out.println("Matrix B");
		Matrix matB = getMatrix(sc);

		System.out.println("Addition yields: ");
		matA.add(matB).print();
		System.out.println();
		System.out.println("Subtraction yields: ");
		matA.sub(matB).print();
		System.out.println();
		System.out.println("Multiplication yields: ");
		matA.multiply(matB).print();
		System.out.println();
		System.out.println("Transpose yields: ");
		matA.transpose().print();
		System.out.println();
		
		System.out.println("------------");
		System.out.println(matA.isSquareMatrix()? "A is a square Matrix" : "A is not a square Matrix");
		System.out.println(matB.isSquareMatrix()? "B is a square Matrix" : "B is not a square Matrix");
		System.out.println();
		System.out.println(matA.isDiagonalMatrix()? "A is a diagonal Matrix" : "A is not a diagonal Matrix");
		System.out.println(matB.isDiagonalMatrix()? "B is a diagonal Matrix" : "B is not a diagonal Matrix");
		System.out.println();
		System.out.println(matA.isIdentity()? "A is a identity Matrix" : "A is not a identity Matrix");
		System.out.println(matB.isIdentity()? "B is a identity Matrix" : "B is not a identity Matrix");
		System.out.println();

		sc.close();
	}
	
	static Matrix getMatrix(Scanner sc) {
		
		System.out.print("Insert no. of row\t: ");
		int row = sc.nextInt();
		System.out.print("Insert no. of column\t: ");
		int col = sc.nextInt();
		int[][] mat = new int[row][col];
		System.out.println("Insert the matrix");
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				mat[i][j] = sc.nextInt();
			}
		}
		return new Matrix(mat);
	}
	
	
	
}

class Matrix {
	int[][] data;
	Matrix(int[][] data) {
		this.data = data;
	}
	
	Matrix add(Matrix other) {
		Matrix result = new Matrix(new int[data.length][data[0].length]);
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				result.data[i][j]  = data[i][j] + other.data[i][j];
			}
		}
		return result;
	}
	Matrix sub(Matrix other) {
		Matrix result = new Matrix(new int[data.length][data[0].length]);

		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				result.data[i][j] = data[i][j] - other.data[i][j];
			}
		}
		return result;
	}
	Matrix multiply(int scalar) {
		Matrix result = new Matrix(new int[data.length][data[0].length]);
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				result.data[i][j] = data[i][j] * scalar;
			}
		}
		return result;
	}
	Matrix multiply(Matrix other) {
		if (data[0].length != other.data.length) {
			System.out.println("Matrices can't be muliplied");
		}
		Matrix result = new Matrix(new int[data.length][other.data[0].length]);
		
		
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < other.data[0].length; j++) {
				for(int k = 0; k < data[0].length; k++) {
					result.data[i][j] += data[i][k] * other.data[k][j];
				}
			}
		}
		return result;
	}
	
	Matrix transpose() {
		Matrix result = new Matrix(new int[data.length][data[0].length]);
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				result.data[i][j] = data[j][i];
			}
		}
		return result;
	}
	
	boolean isSquareMatrix() {
		return data.length == data[0].length;
	}
	
	boolean isDiagonalMatrix() {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				if (i == j) continue;
				if (data[i][j] != 0) return false;
			}
		}
		return true;
	}
	boolean isIdentity() {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				if (i == j) {
					if (data[i][j] != 1) return false;
				}else if (data[i][j] != 0) return false;
			}
		}
		return true;
	}
	
	void print() {
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[0].length; j++) {
				System.out.print(data[i][j] + " ");
			}
			System.out.println();
		}
	}
}