package com.etisalat.nd.flte;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import java.io.*;

public class SFTPFile
    {
    public static void main(String args[])
    {
    String user = "xxxx";
    String password = "xxxyyyzzz";
    String host = "192.168.0.106";
    int port=22;

    String remoteFile="/home/john/test.txt";

    try
        {
        JSch jsch = new JSch();
        Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
        System.out.println("Establishing Connection...");
        session.connect();
            System.out.println("Connection established.");
        System.out.println("Creating SFTP Channel.");
        ChannelSftp sftpChannel = (ChannelSftp) session.openChannel("sftp");
        sftpChannel.connect();
        System.out.println("SFTP Channel created.");


        InputStream out= null;
        out= sftpChannel.get(remoteFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(out));
        String line;
        while ((line = br.readLine()) != null)
            System.out.println(line);
        br.close();
        }
    catch(Exception e){System.err.print(e);}
    }
   }
