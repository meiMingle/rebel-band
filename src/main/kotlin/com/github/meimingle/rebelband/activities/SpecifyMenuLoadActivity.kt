package com.github.meimingle.rebelband.activities;

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.Anchor
import com.intellij.openapi.actionSystem.Constraints
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.impl.ActionManagerImpl
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.intellij.ui.ExperimentalUI

internal class SpecifyMenuLoadActivity : ProjectActivity {
    override suspend fun execute(project: Project) {
        if (ExperimentalUI.isNewUI()) {
            val actionManager = ActionManager.getInstance() as ActionManagerImpl
            val group = actionManager.getAction("MainToolbarRight")
            // val action = actionManager.getAction("rebel.toolbar.runner.group")
            // val action = JXRComboBoxAction("Select Rebel Agents")

            val action = actionManager.getAction("rebel.menu.run.group")
            if (group is DefaultActionGroup && action != null) {
                if (!group.containsAction(action)) {
                    actionManager.addToGroup(group, action, Constraints(Anchor.AFTER, "NewUiRunWidget"))
                }
            }
        }
    }
}
