import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;

import java.text.SimpleDateFormat;

public class CurrentDateAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
       // 获取当前的时间
        // java.util.Date date = new java.util.Date();
        // 格式化时间 按照 年月日 时分秒 格式显示
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new java.util.Date());
        //获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        Caret caret = editor.getCaretModel().getCurrentCaret();
        int offset = caret.getOffset();
        final Document document = editor.getDocument();
        Runnable runnable = () -> document.insertString(offset, strDate);
        WriteCommandAction.runWriteCommandAction(editor.getProject(), runnable);
    }
}
