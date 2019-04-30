//
// Created by Isaac on 4/28/2019.
//

#ifndef REPSYCH_ASHESIASALIVINGLAB_H
#define REPSYCH_ASHESIASALIVINGLAB_H

#include "string"
using namespace std;



class AshesiAsALivingLab {
private:
    string email;
    string password;
    double credit;
    int binCapacity;
    string status;

public:
    AshesiAsALivingLab();

    AshesiAsALivingLab(string newEmail, string NewPass, double NewCredit, int newCapacity , string NewStatus){
        email= newEmail;
        password = NewPass;
        credit = NewCredit;
        binCapacity = newCapacity;
        status = NewStatus;
    }

    void setEmail(string userEmail){
        email = userEmail;
    }

    void setPassword(string userPassword){
        password = userPassword;
    }

    void setCredit(double newCredit){
        credit = newCredit;
    }


    void setBinCapacity(int binSize){
        binCapacity = binSize;
    }

    void setStatus(string userStatus){
        status = userStatus;
    }

    string getEmail(){
        return email;
    }

    string getPassword(){
        return password;
    }

    double getCredit(){
        return credit;
    }



    int getBinCapacity(){
        return binCapacity;
    }

    string getStatus(){
        return status;
    }

    double creditAccount(){
        double curr_credit = getCredit();
        double new_credit = curr_credit + .20;
        setCredit(new_credit);
    }

    bool VerifyLogin(string Email, string Password){
        bool verify = false;
        if (Email == getEmail() && Password == getPassword()){
            verify = true;
        }else {
            verify = false;
        }
        return verify;
    }

    void registerUser(string UseEmail,string UsePassword, string ConfirmPassword){
        if(UsePassword == ConfirmPassword){
            setEmail(UseEmail);
            setPassword(UsePassword);
        }
    }

    void Purchase(string Stakeholder,double amount){
        if(getCredit() == 0){
            throw "Insufficient Funds";
        }else {
            double bal = getCredit() - amount;
            setCredit(bal);
        }
    }





};


#endif //REPSYCH_ASHESIASALIVINGLAB_H
