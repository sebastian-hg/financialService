package com.mycompany.financialservices.scheduled;

@FunctionalInterface
public interface ProcessingCryptoAndSavingToDatabase {
    void execute() throws Exception;
}
