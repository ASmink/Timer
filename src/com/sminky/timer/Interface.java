/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sminky.timer;

import com.sun.awt.AWTUtilities;
import java.awt.Point;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Sminky
 */
public class Interface extends javax.swing.JFrame {

    static Point mouseDownCompCoords;
    boolean waitStatus;
    boolean stopStatus;
    int counter = 0;
    TrayIcon trayIcon;
    SystemTray tray;

    public Interface() {
        initComponents();
        setLocationRelativeTo(null);

        mouseDownCompCoords = null;

        back.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                mouseDownCompCoords = null;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mouseDownCompCoords = e.getPoint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });

        back.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                Point currCoords = e.getLocationOnScreen();
                setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
            }
        });
    }
    MinThread thread1 = new MinThread();
    StopThread thread2 = new StopThread();

    class MinThread extends Thread {

        @Override
        public void run() {
            try {
                int hours = Integer.parseInt(hourField.getText());
                int mins = Integer.parseInt(minField.getText());
                int secs = Integer.parseInt(secField.getText());
                int max = (hours * 60 * 60) + (mins * 60) + secs;

                bar.setMaximum(max);
                int counter = 0;
                while (true) {
                    if (waitStatus) {
                        MinThread.sleep(50);
                    } else {
                        counter++;

                        if (secs == 0 && mins == 0 && hours == 0) {
                            toFront();

                            if (addMsg.isSelected()) {
                                JOptionPane.showMessageDialog(null, "Countdown Reached Its End!\n\nMessage:\n" + msg.getText(), "Done", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Countdown Reached Its End!", "Done", JOptionPane.INFORMATION_MESSAGE);
                            }

                            hourField.setEnabled(true);
                            minField.setEnabled(true);
                            secField.setEnabled(true);
                            start.setEnabled(true);
                            stop.setEnabled(false);
                            pause.setEnabled(false);
                            addMsg.setEnabled(true);
                            msg.setEnabled(false);
                            msg.setText("");
                            addMsg.setSelected(false);
                            bar.setValue(0);
                            start.setText("Start");
                            thread1.stop();
                        } else if (secs == 0 && mins == 0) {
                            secs = 59;
                            mins = 59;
                            hours--;
                        } else if (secs == 0) {
                            secs = 59;
                            mins--;
                        } else {
                            secs--;
                        }
                        String sHours = Integer.toString(hours);
                        String sMins = Integer.toString(mins);
                        String sSecs = Integer.toString(secs);

                        if (sHours.length() == 1) {
                            sHours = "0" + sHours;
                        }

                        if (sMins.length() == 1) {
                            sMins = "0" + sMins;
                        }

                        if (sSecs.length() == 1) {
                            sSecs = "0" + sSecs;
                        }

                        hourField.setText(sHours);
                        minField.setText(sMins);
                        secField.setText(sSecs);
                        bar.setValue(counter);
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
    }

    class StopThread extends Thread {

        @Override
        public void run() {
            try {
                int hours = Integer.parseInt(hourField1.getText());
                int mins = Integer.parseInt(minField1.getText());
                int secs = Integer.parseInt(secField1.getText());
                int milli = Integer.parseInt(milliField1.getText());

                while (true) {
                    if (stopStatus) {
                        MinThread.sleep(50);
                    } else {
                        if (milli == 99 && secs == 59 && mins == 59) {
                            milli = 0;
                            secs = 0;
                            mins = 0;
                            hours++;
                        } else if (milli == 99 && secs == 59) {
                            milli = 0;
                            secs = 0;
                            mins++;
                        } else if (milli == 99) {
                            milli = 0;
                            secs++;
                        } else {
                            milli++;
                        }
                        String sHours = Integer.toString(hours);
                        String sMins = Integer.toString(mins);
                        String sSecs = Integer.toString(secs);
                        String sMilli = Integer.toString(milli);

                        if (sHours.length() == 1) {
                            sHours = "0" + sHours;
                        }

                        if (sMins.length() == 1) {
                            sMins = "0" + sMins;
                        }

                        if (sSecs.length() == 1) {
                            sSecs = "0" + sSecs;
                        }

                        if (sMilli.length() == 1) {
                            sMilli = "0" + sMilli;
                        }

                        hourField1.setText(sHours);
                        minField1.setText(sMins);
                        secField1.setText(sSecs);
                        milliField1.setText(sMilli);
                        Thread.sleep(10);
                    }
                }
            } catch (Exception e) {
                System.err.println("Error: " + e);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        hourField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        secField = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        minField = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        msg = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        stop = new javax.swing.JButton();
        start = new javax.swing.JButton();
        pause = new javax.swing.JButton();
        addMsg = new javax.swing.JCheckBox();
        bar = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        hourField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        secField1 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        minField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        history = new javax.swing.JTextArea();
        jTextField6 = new javax.swing.JTextField();
        milliField1 = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        transparencySlider = new javax.swing.JSlider();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Timers");
        setResizable(false);
        setUndecorated(true);

        hourField.setFont(new java.awt.Font("LCD", 0, 48)); // NOI18N
        hourField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hourField.setText("00");
        hourField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Countdown Timer:");

        secField.setFont(new java.awt.Font("LCD", 0, 48)); // NOI18N
        secField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        secField.setText("00");
        secField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jTextField4.setFont(new java.awt.Font("LCD", 0, 48)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText(":");
        jTextField4.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField4.setEnabled(false);

        minField.setFont(new java.awt.Font("LCD", 0, 48)); // NOI18N
        minField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        minField.setText("00");
        minField.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        jTextField2.setFont(new java.awt.Font("LCD", 0, 48)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText(":");
        jTextField2.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField2.setEnabled(false);

        msg.setEnabled(false);

        jPanel1.setLayout(new java.awt.GridLayout(1, 4));

        stop.setText("Stop");
        stop.setEnabled(false);
        stop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopActionPerformed(evt);
            }
        });
        jPanel1.add(stop);

        start.setText("Start");
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        jPanel1.add(start);

        pause.setText("Pause");
        pause.setEnabled(false);
        pause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseActionPerformed(evt);
            }
        });
        jPanel1.add(pause);

        addMsg.setText("Add Message to Timer");
        addMsg.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                addMsgItemStateChanged(evt);
            }
        });

        bar.setStringPainted(true);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(hourField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(minField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(secField, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(msg, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(addMsg)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hourField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(minField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(secField, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(addMsg)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        back.addTab("Countdown Timer", jPanel2);

        hourField1.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        hourField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        hourField1.setText("00");
        hourField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        hourField1.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Stop Watch:");

        secField1.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        secField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        secField1.setText("00");
        secField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        secField1.setEnabled(false);

        jTextField5.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.setText(":");
        jTextField5.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField5.setEnabled(false);

        minField1.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        minField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        minField1.setText("00");
        minField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        minField1.setEnabled(false);

        jTextField3.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField3.setText(":");
        jTextField3.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField3.setEnabled(false);

        jPanel4.setLayout(new java.awt.GridLayout(1, 4));

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton1);

        jButton2.setText("Split");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2);

        jButton3.setText("Pause");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton3);

        jButton4.setText("Start");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4);

        history.setColumns(20);
        history.setRows(5);
        history.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        history.setEnabled(false);
        jScrollPane1.setViewportView(history);

        jTextField6.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText(".");
        jTextField6.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextField6.setEnabled(false);

        milliField1.setFont(new java.awt.Font("LCD", 0, 40)); // NOI18N
        milliField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        milliField1.setText("00");
        milliField1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        milliField1.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(hourField1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(minField1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(secField1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(milliField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jTextField5)
                    .addComponent(minField1)
                    .addComponent(secField1)
                    .addComponent(jTextField3)
                    .addComponent(jTextField6)
                    .addComponent(milliField1)
                    .addComponent(hourField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        back.addTab("Stop Watch", jPanel3);

        jButton5.setText("About");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel3.setText("Set Window Transparency:");

        transparencySlider.setMaximum(9);
        transparencySlider.setMinorTickSpacing(1);
        transparencySlider.setPaintLabels(true);
        transparencySlider.setPaintTicks(true);
        transparencySlider.setSnapToTicks(true);
        transparencySlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                transparencySliderStateChanged(evt);
            }
        });

        jButton6.setText("Exit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Hide");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel4.setText("Set Look and Feel:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nimbus", "Windows Classic", "Windows", "Motif", "Metal" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel4)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(transparencySlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(transparencySlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        back.addTab("Options", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(back)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        if (hourField.getText().equals("")) {
            hourField.setText("00");
        }

        if (minField.getText().equals("")) {
            minField.setText("00");
        }

        if (secField.getText().equals("")) {
            secField.setText("00");
        }

        int hourContent = Integer.parseInt(hourField.getText());
        int minContent = Integer.parseInt(minField.getText());
        int secContent = Integer.parseInt(secField.getText());

        if (secContent > 59) {
            int ans = (int) secContent / 60;
            minContent = minContent + ans;
            secContent = secContent % 60;
        }

        if (minContent > 59) {
            int ans = (int) minContent / 60;
            hourContent = hourContent + ans;
            minContent = minContent % 60;
        }

        secField.setText(Integer.toString(secContent));
        minField.setText(Integer.toString(minContent));
        hourField.setText(Integer.toString(hourContent));

        hourField.setEnabled(false);
        minField.setEnabled(false);
        secField.setEnabled(false);
        start.setEnabled(false);
        stop.setEnabled(true);
        pause.setEnabled(true);
        waitStatus = false;
        addMsg.setEnabled(false);
        msg.setEnabled(false);

        if (start.getText().equals("Start")) {
            thread1.stop();
            thread1 = new MinThread();
            thread1.start();
        }
    }//GEN-LAST:event_startActionPerformed

    private void pauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseActionPerformed
        hourField.setEnabled(false);
        minField.setEnabled(false);
        secField.setEnabled(false);
        start.setEnabled(true);
        stop.setEnabled(true);
        pause.setEnabled(false);
        start.setText("Resume");
        waitStatus = true;
    }//GEN-LAST:event_pauseActionPerformed

    private void stopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopActionPerformed
        thread1.stop();
        hourField.setEnabled(true);
        minField.setEnabled(true);
        secField.setEnabled(true);
        start.setEnabled(true);
        stop.setEnabled(false);
        pause.setEnabled(false);
        hourField.setText("00");
        minField.setText("00");
        secField.setText("00");
        start.setText("Start");
        addMsg.setEnabled(true);
        msg.setEnabled(false);
        msg.setText("");
        addMsg.setSelected(false);
        bar.setValue(0);
    }//GEN-LAST:event_stopActionPerformed

    private void addMsgItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_addMsgItemStateChanged
        if (addMsg.isSelected()) {
            msg.setEnabled(true);
            msg.requestFocus();
        } else {
            msg.setEnabled(false);
        }
    }//GEN-LAST:event_addMsgItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        stopStatus = false;

        thread2.stop();
        thread2 = new StopThread();
        thread2.start();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        counter++;
        String historyString = Integer.toString(counter) + ": " + hourField1.getText() + ":" + minField1.getText() + ":" + secField1.getText() + "." + milliField1.getText() + "\n";
        history.append(historyString);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        stopStatus = true;

        milliField1.setText("00");
        secField1.setText("00");
        minField1.setText("00");
        hourField1.setText("00");

        history.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        stopStatus = true;
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        JOptionPane.showMessageDialog(null, "Version: 3.1\nLogic Programmer: André Smink (Sminky)\nGUI Programmer: André Smink\nTroubleshooting: Simon Barkhuizen (Phyire)\nConcept: André Smink (Sminky), Simon Barkhuizen (Phyire)");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void transparencySliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_transparencySliderStateChanged
        if (transparencySlider.getValue() == 0) {
            AWTUtilities.setWindowOpacity(this, 0.1f);
        } else if (transparencySlider.getValue() == 1) {
            AWTUtilities.setWindowOpacity(this, 0.2f);
        } else if (transparencySlider.getValue() == 2) {
            AWTUtilities.setWindowOpacity(this, 0.3f);
        } else if (transparencySlider.getValue() == 3) {
            AWTUtilities.setWindowOpacity(this, 0.4f);
        } else if (transparencySlider.getValue() == 4) {
            AWTUtilities.setWindowOpacity(this, 0.5f);
        } else if (transparencySlider.getValue() == 5) {
            AWTUtilities.setWindowOpacity(this, 0.6f);
        } else if (transparencySlider.getValue() == 6) {
            AWTUtilities.setWindowOpacity(this, 0.7f);
        } else if (transparencySlider.getValue() == 7) {
            AWTUtilities.setWindowOpacity(this, 0.8f);
        } else if (transparencySlider.getValue() == 8) {
            AWTUtilities.setWindowOpacity(this, 0.9f);
        } else if (transparencySlider.getValue() == 9) {
            AWTUtilities.setWindowOpacity(this, 1.0f);
        }
    }//GEN-LAST:event_transparencySliderStateChanged

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        setState(Interface.ICONIFIED);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        System.out.println(jComboBox1.getSelectedItem());
        if (jComboBox1.getSelectedItem().toString().equals("Nimbus")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Windows Classic")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Windows")) {
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Motif")) {
            System.out.println("Motif Selected");
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (jComboBox1.getSelectedItem().toString().equals("Metal")) {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
                SwingUtilities.updateComponentTreeUI(this);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedLookAndFeelException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Interface().setVisible(true);

            }
        });

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox addMsg;
    private javax.swing.JTabbedPane back;
    private javax.swing.JProgressBar bar;
    private javax.swing.JTextArea history;
    private javax.swing.JTextField hourField;
    private javax.swing.JTextField hourField1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField milliField1;
    private javax.swing.JTextField minField;
    private javax.swing.JTextField minField1;
    private javax.swing.JTextField msg;
    private javax.swing.JButton pause;
    private javax.swing.JTextField secField;
    private javax.swing.JTextField secField1;
    private javax.swing.JButton start;
    private javax.swing.JButton stop;
    private javax.swing.JSlider transparencySlider;
    // End of variables declaration//GEN-END:variables
}
