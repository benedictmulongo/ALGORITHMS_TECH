package com.company;

/**
 * Created by ben on 2017-11-17.
 */
public class Knapsack_SimAnnea
{
    int [] weights;
    int [] profits;
    int Bound;
    int Number_of_goods;

    Knapsack_SimAnnea(int [] W, int [] P, int Limit, int N)
    {
        this.weights = new int [N];
        this.profits = new int [N];
        for(int j = 0; j < N; j++)
        {
            this.weights[j] = W[j];
            this.profits[j] = P[j];
        }
        this.Bound = Limit;
        this.Number_of_goods = N;
    }

    public int [] SimulatedAnnealing(int Limit)
    {
        int c = 0;
        double temperature = 10000;
        int [] X = new int [Number_of_goods];
        int current_Weight = 0;
        int [] X_best = new int [Number_of_goods];
        boolean doit = true;
        for(int i = 0; i < Number_of_goods; i++)
        {
            X[i] = 0;
            X_best[i] = 0;
        }
        int base = 0;
        double Alpha = 0.9999;
        while(c < Limit)
        {
            int j = (int)(base + Math.random()*(Number_of_goods - base + 1 - 1));
            int [] Y = new int[Number_of_goods];
            for(int i = 0; i < Number_of_goods; i++)
                Y[i] = X[i];
            Y[j] = 1 - X[j];
          //  System.out.println(Y[j]);
            int check =  ( current_Weight + weights[j]);
            if((Y[j] == 1) && (check > Bound ))
            {
               // Y[j] = -1;
                doit = false;
            }
            if(doit)
            {
                if(Y[j] == 1)
                {
                    for(int i = 0; i < Number_of_goods; i++)
                        X[i] = Y[i];
                    current_Weight = current_Weight + weights[j];
                    if(checkprofit(X,X_best))
                    {
                        for(int i = 0; i < Number_of_goods; i++)
                            X_best[i] = X[i];
                    }
                }
                else
                {
                    double rand = Math.random();
                    double exponent = -(profits[j])/temperature;
                    double e = Math.pow(Math.E,exponent); // e^(-pj/T)
                    if(Double.compare(rand,e) < 0)
                    {
                        for(int i = 0; i < Number_of_goods; i++)
                            X[i] = Y[i];
                        current_Weight = current_Weight - weights[j];
                    }
                }
            }

            c = c + 1;
            temperature = temperature*Alpha;
        }
        return X_best;
    }

    public boolean checkprofit(int [] X, int [] X_best)
    {
        int x_profit = 0;
        int x_best_profit = 0;
        for(int i = 0; i < Number_of_goods; i++)
        {
            x_profit = x_profit + (X[i]*profits[i]) ;
            x_best_profit = x_best_profit + (X_best[i]*profits[i]);
        }

        return (x_profit > x_best_profit);
    }

}
