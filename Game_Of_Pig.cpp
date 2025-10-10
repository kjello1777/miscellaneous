#include <string>
#include <iostream>
#include <cstdlib>

using namespace std;
int rollDie(){
    return rand()%6+1;
}

bool playerTurn(int &playerScore){
    int roundScore = 0;
    string choice;
    
    cout<<"Your Current Score is: "<<playerScore<<endl;
    cout<<"This round you have: "<<roundScore<<endl;
    
    while (true){
        cout<<"\nWould you like to roll or bank? ";
        cin>>choice;
        
        if (choice == "roll"){
            int roll = rollDie();
            cout<<"Die has been cast... "<<roll<<". "<<endl;
            
            if (roll==1){
                cout<<"You rolled a 1! You get a zero for this round!"<<endl;
                roundScore = 0;
                break;
            }
            else{
                roundScore += roll;
                cout<<"This round you have: "<<roundScore<<endl;
            }
            
        }
        else if(choice == "bank"){
            playerScore+=roundScore;
            roundScore = 0;
            break;
            
        }
        else{
            cout<<"Invalid choice. Please type 'roll' or 'bank'."<<endl;
        }
    }
    cout<<"End of your turn. Your total score: "<<playerScore<<"\n***********\n"<<endl;
    return playerScore >=100;
}

bool computerTurn(int &computerScore){
    int roundScore = 0;

    cout<<"Computer Current Score is: "<<computerScore<<endl;

    while (true){
        int roll = rollDie();
        cout<<"Die has been cast... "<<roll<<". \n"<<endl;
            
        if (roll==1){
            cout<<"The computer rolled a 1. End of turn."<<endl;
            roundScore = 0;
            break;
        }
        else{
            roundScore += roll;
                cout<<"This round the computer has: "<<roundScore<<endl;
                
                if (roundScore>15){
                    cout<<"The computer chooses to bank."<<endl;
                    computerScore+=roundScore;
                    break;
                }else{
                    cout<<"The computer chooses to roll again."<<endl;
                }
        }
    }
    cout<<"End of computer turn. Computer total score: "<<computerScore<<"\n***********\n"<<endl;
    return computerScore>=100;
}

int main()
{
    int playerScore = 0;
    int computerScore = 0;
    int turn = 1;
    
    while (true){
        cout<<"Turn "<<turn<<"\n"<<endl;
        
        bool playerWon = playerTurn(playerScore);
        bool computerWon = computerTurn(computerScore);
        
        if (playerWon||computerWon){
            if (playerScore>=100 && computerScore >=100){
                if (playerScore>computerScore){
                    cout<<"Congrats! You win with "<<playerScore<<" points vs computer's "<<computerScore<<"!"<<endl;
                } else if (computerScore>playerScore){
                    cout<<"Good try! Ther computer won with "<<computerScore<<" points vs your "<<playerScore<<endl;
                    
                } else{
                    cout<<"It's a tie! Both scored "<<computerScore<<endl;
                }
                
            }else if (playerScore>=100){
                cout<<"Congratulations! You won in "<<turn<<" turns with "<<playerScore<<" points!"<<endl;
            }else{
                cout<<"Good try! The computer won in "<<turn<<" turns with "<<computerScore<<" points!"<<endl;
            }
            break;
        }
        turn++;
    }
    return 0;
}
