package to.avax.toolbox.gui.wallet.addresses;

import to.avax.avalanche.wallet.HdHelper;
import to.avax.toolbox.gui.wallet.AvaxtoWalletPanel;

import javax.swing.*;
import java.awt.*;

public class AvaxtoWalletAddressPanel extends JPanel {
    AvaxtoWalletPanel parent;
    JPanel addressesButtonPanel;
    JTextArea addressesTextArea;
    private int currentPage;
    JLabel addrPageLabel;
    int addressCountPerPage;
    HdHelper hdHelper;
    private final static int DEFAULT_ADDRESS_COUNT = 20;

    public AvaxtoWalletAddressPanel(AvaxtoWalletPanel parent, int perPage, HdHelper hdh) {
        super();

        this.addressCountPerPage = perPage;
        this.parent = parent;
        this.hdHelper = hdh;
        setLayout(new BorderLayout());
        addressesTextArea = new JTextArea();
        currentPage = 0;
        addressesButtonPanel = new JPanel();
        ((FlowLayout)addressesButtonPanel.getLayout()).setAlignment(FlowLayout.LEFT);
        JButton addrNextButton = new JButton(">>");
        JButton addrPrevButton = new JButton("<<");
        addrPageLabel = new JLabel("1");

        addressesButtonPanel.add(addrPrevButton);
        addressesButtonPanel.add(addrPageLabel);
        addressesButtonPanel.add(addrNextButton);

        setLayout(new BorderLayout());
        add(addressesButtonPanel, BorderLayout.NORTH);

        JScrollPane jspx = new JScrollPane(addressesTextArea);
        add(jspx, BorderLayout.CENTER);

        addrNextButton.addActionListener( e -> {
            currentPage++;
            listAddresses(currentPage, this.addressCountPerPage);
            addrPageLabel.setText(String.valueOf(currentPage + 1));
        });

        addrPrevButton.addActionListener( e -> {
            if (currentPage > 0) {
                currentPage--;
            }
            listAddresses(currentPage, this.addressCountPerPage);
            addrPageLabel.setText(String.valueOf(currentPage + 1));

        });
    }
    public void listAddresses() {
        listAddresses(currentPage, DEFAULT_ADDRESS_COUNT);
    }

    public void listAddresses(int page, int addressCount) {
        int index = (page * addressCount);
        int lastIndex = index + addressCount;

        addressesTextArea.setText("");

        StringBuilder sb = new StringBuilder();
        for (; index<lastIndex; index++) {
            String address = hdHelper.getAddressForIndex(index);
            sb.append(address);
            sb.append("\n");
        }

        addrPageLabel.setText(String.valueOf(page + 1));
        addressesTextArea.setText(sb.toString());
    }
}
