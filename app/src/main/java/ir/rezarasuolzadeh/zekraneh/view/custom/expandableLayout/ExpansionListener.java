package ir.rezarasuolzadeh.zekraneh.view.custom.expandableLayout;

import android.animation.Animator;

public class ExpansionListener implements Animator.AnimatorListener {

    private final ExpandableLayout expandableLayout;
    private int targetExpansion;
    private int state;
    private boolean canceled;

    public ExpansionListener(ExpandableLayout expandableLayout, int targetExpansion) {
        this.expandableLayout = expandableLayout;
        this.targetExpansion = targetExpansion;
    }

    @Override
    public void onAnimationStart(Animator animation) {
        state = targetExpansion == 0 ? State.COLLAPSING : State.EXPANDING;
        expandableLayout.setState(state);
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (!canceled) {
            state = targetExpansion == 0 ? State.COLLAPSED : State.EXPANDED;
            expandableLayout.setState(state);
            expandableLayout.setExpansion(targetExpansion);
        }
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        canceled = true;
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        // nothing to do yet
    }

}
