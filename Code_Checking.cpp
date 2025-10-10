#include <string>
#include <stack>
#include <iostream>
#include <stdexcept>
#include <fstream>
using namespace std;
char closer(char open){
    if (open == '[') return ']';
    if (open == '{') return '}';
    if (open == '(') return ')';
    return '?';
}
int main()
{
    // Enter your code here
    string filename;
    cout<<"Enter file name: ";
    cin>>filename;
    
    ifstream infile;
    infile.open(filename);
    
    if (!infile.is_open()){
        throw runtime_error("Could not open file: "+filename);
    }
    stack<pair<char,int>> symbols;
    string line;
    
    int lineNum = 0;
    
    while (getline(infile, line)){
        lineNum++;
        for (char ch: line){
            if (ch == '{'|| ch == '['|| ch == '('){
                symbols.push({ch, lineNum});
            }
            else if (ch == '}'|| ch == ']'|| ch == ')'){
                if (symbols.empty()){
                    throw runtime_error("Line: "+to_string(lineNum)+" Unexpected "+string(1,ch));

                }
                char open = symbols.top().first;
                int openLine = symbols.top().second;
                symbols.pop();
                
                if (closer(open)!=ch){
                    throw runtime_error("Line: "+to_string(lineNum)+" Expecting "+ string(1, closer(open))+" but found "+ string(1,ch));
                }
            }
        }
    }
    
    if (!symbols.empty()){
        char open = symbols.top().first;
        int openLine = symbols.top().second;
        throw runtime_error("Line: "+to_string(lineNum)+" Expecting "+ string(1, closer(open))+" but reached end of file");
    }
    cout<<"Your code looks good!"<<endl;
    
    return 0;
}
