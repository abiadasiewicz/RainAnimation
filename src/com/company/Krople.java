package com.company;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Krople {

public void rusz (JPanel container)
{
    y += dy;

    if(y>container.getHeight())
    {
        x=new Random().nextInt(container.getWidth());
        y=0;
    }

}

    int x = new Random().nextInt(700);
    int y = 0;
    int dy = new Random().nextInt(20)+5;

}
