#include <cstdlib>
#include <vector>
#include <string>
#include <stdexcept>
#include <iostream>
using namespace std;
int BOARD_SIZE = 15;
void printBoard(vector<vector<string>> &board){
    cout<<"**************************"<<endl;

    for (int i = 0; i<board.size(); i++){
        for (int j = 0; j<board[0].size(); j++){
            cout<<board[i][j]<<" ";
        }
        cout<<endl;
    }
    cout<<"**************************\n"<<endl;

}
int isAlive(vector<vector<string>> board, int x, int y){
    int numAlive = 0;
    bool curAlive;
    if (board[x][y]=="X"){
        curAlive = true;
    }
    else if (board[x][y]==" "){
        curAlive = false;
    }
    if (curAlive){
        for (int i = x-1; i<=x+1; i++){
            for (int j = y-1; j<=y+1; j++){
                try{
                    board.at(i).at(j);
                    if (i==x&& j==y) continue;
                    else if (board[i][j]=="X"){
                        numAlive++;
                    }
                }
                catch(out_of_range e){
                    e.what();
                }
            }
        }
        if (!(numAlive ==2 || numAlive ==3)){
            board[x][y]=" ";
        }
    }
    else if (!curAlive){
        for (int i = x-1; i<=x+1; i++){
            for (int j = y-1; j<=y+1; j++){
                try{
                    board.at(i).at(j);
                    if (i==x&& j==y) continue;
                    else if (board[i][j]=="X"){
                        numAlive++;
                    }
                }
                catch(out_of_range e){
                    e.what();
                }
            }
        }
        
    }
    return numAlive;
}

vector<vector<string>> setUpBoard(){
    int size = 15;
    vector<vector<string>> board (size, vector<string>(size, " "));
    
    int alive;
    cout<<"Please enter number of active cells between 1 and 225: ";
    cin>>alive;
    cin.ignore();
    
    cout<<"alive: "<<alive<<endl;
    
    while(alive>0){
        int randY= rand()%size;
        int randX = rand()%size;
        
        if (board[randX][randY]!="X"){
            board[randX][randY]="X";
            alive--;
        }
        
        if (alive<0){
            break;
        }
    }
    printBoard(board);
    return board;
}

void updateBoard(vector<vector<int>> alives, vector<vector<string>> &board){
    
    for (int i =0; i<board.size(); i++){
        for (int j =0; j<board[0].size(); j++){
            if((alives.at(i).at(j)!=3&&alives.at(i).at(j)!=2)&&(board.at(i).at(j)=="X")){
                board.at(i).at(j)=" ";        
            }
            else if (alives.at(i).at(j)==3&&board.at(i).at(j)==" "){
                board.at(i).at(j)="X";
            }
        }
    }
    
    printBoard(board);
}
int main()
{
    vector<vector<string>> board = setUpBoard();
    vector<vector<int>> alives (board.size(), vector<int>(board.size(), -1));
    isAlive(board, 14,14);
    string choice;
    while (true){
        cout<<"Press Enter to continue (exit to quit) ";
        getline(cin,choice);
        if (choice == "exit"){
            break;
        }
        for (int i =0; i<board.size(); i++){
            for (int j =0; j<board[0].size(); j++){
                alives.at(i).at(j)=isAlive(board, i, j);
            }
        }
        updateBoard(alives, board);
    }
    
    return 0;
}
