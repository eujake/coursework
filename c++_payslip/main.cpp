#include <cstdio>
#include <stdio.h>
#include <cstdlib>
#include <iostream>
#include <sstream>
#include <locale.h>
using namespace std;

const int FIXED = 800; // SSS, Philhealth, Pag-ibig.
// Class 
class Payslip
{
private:  
    string name, payGrade;
    int overTimeHour;
    float grossPay, netPay, withHoldingTax, taxRate, OTpay, basicSalary;
public:
    void determinePayGradeAndTaxRate(float basicSalarys);
    void computePay(string names, int overTimeHour, float basicSalarys);

    /* GET */
    string get_name();
    int get_overTimeHour();
    float get_grossPay();
    float get_netPay();
    float get_withHoldingTax();
    float get_OTpay();
    float get_basicSalary();
    string get_payGrade();
    /* GET */

};



void Payslip    ::  determinePayGradeAndTaxRate(float basicSalarys)
{   
    basicSalary =   basicSalarys;
    int salaryIncre = 0;
    taxRate = 0.1;

    for(int i = 0; i < 10; i++ )
    {
        if ( 10000+salaryIncre <= basicSalary && basicSalary < 15000+salaryIncre)
        {
            if ( i%2 == 0)
            {
                payGrade    =   "A";
                taxRate += (0.05)*(i/2);  
            }
            else
            {
                payGrade    =   "B";
                taxRate += (0.05)*(i-1)/2;  
            }
                      
        }
        salaryIncre += 5000;
    } 

}

void Payslip    ::  computePay(string names, int overTimeHours, float basicSalarys)
{
    /**Gross pay = basic salary + OT pay
OT pay = no. of OT hours * 1% of basic salary
Net pay = gross pay – withholding tax – fixed values
Withholding tax = gross pay * tax rate
**/
    name            =   names;
    overTimeHour    =   overTimeHours;      //  From input
    basicSalary     =   basicSalarys;

    OTpay           =   overTimeHour    *   (0.01*basicSalary);
    grossPay        =   basicSalary     +   OTpay;

    withHoldingTax  =   grossPay        *   taxRate;
    netPay          =   grossPay        -   withHoldingTax  -   FIXED;
}


/** Get function method **/


string Payslip  :: get_name()
{
    ostringstream display;

    display << name;

    return display.str();
}
int Payslip  :: get_overTimeHour()
{
    return overTimeHour;
}
float Payslip  :: get_grossPay()
{
    return grossPay;
}
float Payslip  :: get_netPay()
{
    return netPay;
}
float Payslip  :: get_withHoldingTax()
{
    return withHoldingTax;
}

float Payslip  :: get_OTpay()
{
    return OTpay;
}
float Payslip  :: get_basicSalary()
{
    return basicSalary;
}

string Payslip  ::  get_payGrade()
{
    ostringstream display;

    display << payGrade;

    return display.str();
}

int main()
{

    Payslip payObject;
    float basicSalary_main  =   0;
    int otHour_main         =   0;
    string name_main;

    cout << "Enter your name: ";
    getline(cin, name_main);

    cout << "Enter the no. of over time hour: ";
    cin  >> otHour_main;

    while( otHour_main < 1)
    {
        cout << "Please enter the over time hour not less than a hour.";
        cin  >> otHour_main;
    }

    cout << "Enter your basic salary(Not less than 10K): ";
    cin  >> basicSalary_main;

    while( basicSalary_main < 10000)
    {
        cout << "Please enter your salary of not less than 10K.";
        cin  >> basicSalary_main;
    }

    payObject.determinePayGradeAndTaxRate(basicSalary_main);
    payObject.computePay(name_main, otHour_main, basicSalary_main);


    cout << "Employee Name      :   "<<    payObject.get_name() << endl;

    printf("Basic Salary        :   %.2f"  ,    payObject.get_basicSalary()     );
    cout << endl;   

    cout << "Pay Grade          :   "   << payObject.get_payGrade() << endl;

    printf("No. of OT Hours     :   %.2d"    ,  payObject.get_overTimeHour()  );
    cout << endl;

    printf("OT Pay              :   %.2f"  ,    payObject.get_OTpay()           );
    cout << endl;

    printf("Gross Pay           :   %.2f"  ,    payObject.get_grossPay()        );
    cout << endl;

    printf("Withholding Tax     :   %.2f"  ,    payObject.get_withHoldingTax()  );
    cout << endl;

    printf("Net Pay             :   %.2f"  ,    payObject.get_netPay()          );
    cout << endl;



    return EXIT_SUCCESS;
}
