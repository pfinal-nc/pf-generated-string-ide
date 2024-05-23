import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;

public class CurrentTimestampAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
        //获取当前编辑器对象
        Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        //获取当前时间戳
        long timestamp = System.currentTimeMillis() / 1000;
        String timestampStr = String.valueOf(timestamp);
        Caret caret = editor.getCaretModel().getCurrentCaret();
        int offset = caret.getOffset();
        final Document document = editor.getDocument();
        Runnable runnable = () -> document.insertString(offset, timestampStr);
        WriteCommandAction.runWriteCommandAction(editor.getProject(), runnable);
    }
}
