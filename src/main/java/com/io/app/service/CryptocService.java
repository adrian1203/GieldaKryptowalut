package com.io.app.service;


import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.MoreExecutors;
import io.undertow.util.SameThreadExecutor;
import org.bitcoinj.core.*;
import org.bitcoinj.crypto.KeyCrypterException;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.store.MemoryBlockStore;
import org.bitcoinj.utils.BriefLogFormatter;
import org.bitcoinj.wallet.KeyChain;
import org.bitcoinj.wallet.UnreadableWalletException;
import org.bitcoinj.wallet.Wallet;
import org.bitcoinj.wallet.listeners.WalletCoinsReceivedEventListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class CryptocService {

    /*
    * Info about creating and sending btc in regtest (and in other but especially in regtest):
    * We can send first BTC to our wallet from cmd or powerShell, steps to do:
    * 1) bitcoind -regtest  => start regtest instance of bitcoin in backgroun, new terminal needs to be open!!!
    * 2) bitcoin-cli -regtest generate 101   => generate 101 blocks, your wallet gets immadetialy 50BTC reward
    * 3) bitcoin-cli -regtest getbalance   => check balance of account
    * 4) bitcoin-cli -regtest sendtoadress "ADRESS_FROM_HERE" X    => where X is amount of mBTC and "ADD.." is adress from GetWalletAdress()
    * The most important - each of us should work only on his own geenrated wallet, because each of us has own and different
    * blockchain generated! So in main() change "kuba" to for example "adrian" or "jan" or "kamil"!
    *
    * NOTE: After SendToAddress(..) you have to confirm transaction in shell by typing:
    * bitcoin-cli -regtest generate 1
    */


    /* Load wallet from name, if doesn't exist create new one */
    public Wallet LoadWallet(String username, NetworkParameters netParams){

        Wallet wallet = null;
        final File walletFile = new File("wallets/"+username+".wallet");

        try{
            wallet = new Wallet(netParams);


            if(walletFile.exists())
            {
                wallet = Wallet.loadFromFile(walletFile);
                System.out.println("Wallet loaded!");
            }
            else
            {
                wallet.addKey(new ECKey());
                System.out.println("Wallet created!");
            }

            wallet.saveToFile(walletFile);

        }catch (IOException | UnreadableWalletException e ) {System.out.println("Unable to create or load wallet file.");}

        return wallet;
    }

    /* Get current adress of wallet*/
    public String GetWalletAdress(String username,NetworkParameters netParams){
        Wallet wallet = LoadWallet(username,netParams);
        String adress  = wallet.currentAddress(KeyChain.KeyPurpose.RECEIVE_FUNDS).toString();
        System.out.println("WALLET ADRESS: "+ adress);
        return adress;
    }

    /* Get balance of current wallet */
    public long GetWalletBalance(String username, NetworkParameters netParams){
        Wallet wallet = LoadWallet(username,netParams);
        long value = wallet.getBalance().value;
        System.out.println("WALLET COINS: " + value + " satoshi (1TBC=100'000'000satoshi)");
        return value;
    }

    /* Save current state of wallet */
    public void SaveWallet(Wallet wallet, String username){
        final File walletFile = new File("wallets/"+username+".wallet");

        try {
            wallet.saveToFile(walletFile);
        }catch (IOException e) {
            System.out.println("Can't save wallet info");
        }
    }

    /* Refresh wallet from net and save to file updated wallet state */
    public void RefreshWallet(NetworkParameters netparams, String nickname){

        Wallet wallet = LoadWallet(nickname,netparams);

        System.out.println("START REFRESH...");
        File file = new File("wallets/"+nickname+".wallet");
        BlockStore blockStore = new MemoryBlockStore(netparams);
        BlockChain chain = null;

        try{
            chain = new BlockChain(netparams, wallet, blockStore);
        }catch (BlockStoreException e) {
            System.out.println("Block store error!");
        }

        final PeerGroup peerGroup = new PeerGroup(netparams, chain);
        peerGroup.startAsync();

        wallet.addCoinsReceivedEventListener(new WalletCoinsReceivedEventListener() {
            @Override
            public synchronized void onCoinsReceived(Wallet w, Transaction tx, Coin prevBalance, Coin newBalance) {
                System.out.println("\nReceived tx " + tx.getHashAsString());
                System.out.println(tx.toString());
            }
        });

        peerGroup.downloadBlockChain();
        peerGroup.stopAsync();
        try {
            wallet.saveToFile(file);
        }catch (IOException e){
            System.out.println("RefreshWallet: file not saved!");
        }
        System.out.println("\nDone Refreshing Wallet!\n");
        System.out.println(wallet.toString());

    }

    /* Send bitcoins to address */
    public void SendToAdress(Wallet wallet,String username, NetworkParameters netParams,long amountToSend,String recipient){

        BlockStore blockStore = new MemoryBlockStore(netParams);
        Coin coins = Coin.valueOf(amountToSend);
        //String tmp  = "mpGV7f5iJRxh5Mg75yvhVZ6b9VDQg1wLjq";
        BlockChain chain = null;

        try {
             chain = new BlockChain(netParams, wallet, blockStore);
        } catch (BlockStoreException e) {
            System.out.println("Blockstore exception in sendToAddress!");
        }

        final PeerGroup peerGroup = new PeerGroup(netParams, chain);
        peerGroup.startAsync();

        Address recipientAddress = new Address(netParams, recipient);

        try{
            Wallet.SendResult sendResult = wallet.sendCoins(peerGroup,recipientAddress,coins);
            checkNotNull(sendResult);
        } catch (InsufficientMoneyException e) {
            System.out.println("You don't have enough satoshii in wallet!");
        }

        peerGroup.downloadBlockChain();
        peerGroup.stopAsync();
        SaveWallet(wallet,username);
        System.out.println("Send oparation success!");
    }


}
