#include <iostream>
#include <string>
#include <vector>
#include <queue>
using namespace std;
int main()
{
    
    int totalCustomers;
    cout<<"How many customers do you want to simulate? ";
    cin>>totalCustomers;
    int time = 1;
    int nextCust = 1;
    int totalServed = 0;
    
    queue<int> q1,q2,q3;
    
    q1.push(nextCust++);
    while (totalServed<totalCustomers){
        if (time%12==0 && !q1.empty()){
            cout<<"Cashier 1 served customer "<<q1.front()<<endl;
            q1.pop();
            totalServed ++;
        }
        if (time%19==0 && !q2.empty()){
            cout<<"Cashier 2 served customer "<<q2.front()<<endl;
            q2.pop();
            totalServed ++;
        }if (time%25==0 && !q3.empty()){
            cout<<"Cashier 3 served customer "<<q3.front()<<endl;
            q3.pop();
            totalServed ++;
        }
        if (time%5==0&&nextCust<=totalCustomers){
            if (q1.size()<q2.size()&&q1.size()<q3.size()){
                q1.push(nextCust);
            }
            else if (q2.size()<q3.size()){
                q2.push(nextCust);
            }
            else{
                q3.push(nextCust);
            }
            nextCust++;
        }
        time++;
    }
    cout<<"It took a total of "<<time<<" ticks to serve all customers."<<endl;
    
    return 0;
}
